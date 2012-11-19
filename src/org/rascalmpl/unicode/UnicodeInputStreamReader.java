package org.rascalmpl.unicode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class UnicodeInputStreamReader extends Reader {
	private static final int maximumBOMLength = 4;
	private static final ByteOrderMarker[] boms = {
		ByteOrderMarker.UTF8,
		ByteOrderMarker.UTF32BE, 
		ByteOrderMarker.UTF32LE, // 32 first, to avoid ambituity with the 16 LE
		ByteOrderMarker.UTF16BE,
		ByteOrderMarker.UTF16LE
	};
	private static final Charset fallbackCharset = Charset.forName("UTF8");
	
	private Reader wrapped;
	public UnicodeInputStreamReader(InputStream in) throws IOException {
		wrapped = detectCharset(in);
	}
	public UnicodeInputStreamReader(InputStream in, String encoding) throws IOException {
		wrapped = removeBOM(in, encoding);
	}

	private static Reader removeBOM(InputStream in, String encoding) throws IOException {
		PushbackInputStream scanner = new PushbackInputStream(in, maximumBOMLength);
		byte[] detectionBuffer = new byte[maximumBOMLength];
		int bufferSize = scanner.read(detectionBuffer);
		ByteOrderMarker b = detectBom(detectionBuffer, bufferSize);
		if (b != null) {
			Charset ref = Charset.forName(encoding);
			if (b.getCharset().equals(ref) || b.getGroup().equals(ref)) {
				scanner.unread(bufferSize - b.getHeaderLength());
				return new InputStreamReader(scanner, b.getCharset());
			}
			else {
				throw new UnsupportedEncodingException("The requested encoding was " + encoding + " but the file contained a BOM for " + b.getCharset().name() + ".");
			}
		}
		else {
			scanner.unread(bufferSize);
			return new InputStreamReader(scanner, encoding);
		}
	}

	private static int detectionBufferSize = 256;
	private static Reader detectCharset(InputStream in) throws IOException {
		PushbackInputStream scanner = new PushbackInputStream(in, detectionBufferSize);
		byte[] detectionBuffer = new byte[detectionBufferSize];
		int bufferSize = scanner.read(detectionBuffer);
		ByteOrderMarker b =detectBom(detectionBuffer, bufferSize);
		if (b != null) {
			// we have to remove the BOM from the front
			scanner.unread(bufferSize - b.getHeaderLength());
			return new InputStreamReader(scanner, b.getCharset());
		}
		Charset cs = detectByContent(detectionBuffer, bufferSize);
		if (cs == null) {
			cs = fallbackCharset;
		}
		scanner.unread(bufferSize);
		return new InputStreamReader(scanner, cs);
	}

	private static Charset detectByContent(byte[] detectionBuffer, int bufferSize) {
		return null;
	}

	private static ByteOrderMarker detectBom(byte[] detectionBuffer, int bufferSize) {
		for (ByteOrderMarker b: boms) {
			if (b.matches(detectionBuffer, bufferSize))
				return b;
		}
		return null;
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return wrapped.read(cbuf, off, len);
	}

	@Override
	public void close() throws IOException {
		wrapped.close();
	}
}
