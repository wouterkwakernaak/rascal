@license{
  Copyright (c) 2009-2013 CWI
  All rights reserved. This program and the accompanying materials
  are made available under the terms of the Eclipse Public License v1.0
  which accompanies this distribution, and is available at
  http://www.eclipse.org/legal/epl-v10.html
}
@contributor{Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI}
@contributor{Bas Basten - Bas.Basten@cwi.nl (CWI)}
@contributor{Paul Klint - Paul.Klint@cwi.nl - CWI}
@contributor{Mark Hills - Mark.Hills@cwi.nl (CWI)}
@contributor{Arnold Lankamp - Arnold.Lankamp@cwi.nl}
module IO

import Exception;

@doc{
Synopsis: Append a value to a file.

Description:
Append a textual representation of some values to an existing or a newly created file:
* If a value is a simple string, the quotes are removed and the contents are de-escaped.
* If a value has a non-terminal type, the parse tree is unparsed to produce a value.
* All other values are printed as-is.
* Each value is terminated by a newline character.

== Encoding ==
The existing file can be stored using any character set possible, if you know the character set, please use [appendFileEnc].
Else the same method of deciding the character set is used as in [readFile].

Pitfalls:
* The same encoding pitfalls as the [readFile] function.
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java void appendToFile(loc file, value V...)
throws PathNotFound(loc file), IO(str msg);

@doc{
Synopsis: Append a value to a file.

Description:
Append a textual representation of some values to an existing or a newly created file:
* If a value is a simple string, the quotes are removed and the contents are de-escaped.
* If a value has a non-terminal type, the parse tree is unparsed to produce a value.
* All other values are printed as-is.
* Each value is terminated by a newline character.

Files are encoded using the charset provided.
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java void appendToFileEnc(loc file, str charset, value V...)
throws PathNotFound(loc file), IO(str msg);

@doc{
Synopsis: Returns all available character sets
}
@javaClass{org.rascalmpl.library.Prelude}
public java set[str] charsets();

@doc{
Synopsis: Returns whether this charset can be used for encoding (use with writeFile)
}
@javaClass{org.rascalmpl.library.Prelude}
public java set[str] canEncode(str charset);


@doc{
Synopsis: Print a value and return true.

Description:
Print a value and return `true`. This is useful for debugging complex Boolean expressions or comprehensions.
The only difference between this function and [$IO/println] is that its return type is `bool` rather than `void`.

Examples:
<screen>
import IO;
bprintln("Hello World");
</screen>
}
public bool bprintln(value arg) 
{
  println(arg);
  return true;
}


/*
 The following functions are identical to printExp and printlnExp, I have removed them -- Paul
@doc{Synopsis: Prints message and returns the value.}
public &T discardPrintExp(str s, &T t){
	print(s);
	return t;
}

@doc{Synopsis: Prints message on a line and returns the value.}
public &T discardPrintlnExp(str s, &T t){
	println(s);
	return t;
}
*/

@doc{
Synopsis: Check whether a given location exists.

Description:
Check whether a certain location exists, i.e., whether an actual file is associated with it.

Examples:

<screen>
import IO;
// Does the library file `IO.rsc` exist?
exists(|std:///IO.rsc|);
</screen>
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java bool exists(loc file);


@doc{
Synopsis: Find a named file in a list of locations.

Examples:

<screen>
import IO;
// Find the file `IO.rsc` in the standard library:
find("IO.rsc", [|std:///|]);
</screen>
}
public loc find(str name, list[loc] path) throws PathNotFound {
  if (dir <- path, f := dir + "/<name>", exists(f)) { 
    return f;
  }
  throw PathNotFound({dir + "/<name>" | dir <- path});
}

@doc{
Synopsis: Check whether a given location is a directory.

Description:
Check whether the location `file` is a directory.
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java bool isDirectory(loc file);

@doc{
Synopsis: Print an indented representation of a value.

Description:
See [$IO/iprintExp] for a version that returns its argument as result
and [$IO/iprintln] for a version that adds a newline
and [$IO/iprintToFile] for a version that prints to a file.

Examples:

<screen>
import IO;
iprint(["fruits", ("spider" : 8, "snake" : 0), [10, 20, 30]]);
</screen>
}
@reflect{for getting IO streams}
@javaClass{org.rascalmpl.library.Prelude}
public java void iprint(value arg); 

@doc{
Synopsis: Print an indented representation of a value to the specified location.

Description:
See [$IO/iprint] for a version that displays the result on the console
and [$IO/iprintExp] for a version that returns its argument as result
and [$IO/iprintln] for a version that adds a newline.

Examples:

<screen>
import IO;
iprintToFile(|file:///tmp/fruits.txt|, ["fruits", ("spider" : 8, "snake" : 0), [10, 20, 30]]);
</screen>
}
@reflect{for getting IO streams}
@javaClass{org.rascalmpl.library.Prelude}
public java void iprintToFile(loc file, value arg); 

@doc{
Synopsis: Print an indented representation of a value and returns the value as result.

Description:
See [$IO/iprintlnExp] for a version that adds a newline.

Examples:

<screen>
import IO;
iprintExp(["fruits", ("spider" : 8, "snake" : 0), [10, 20, 30]]);
</screen>
}
public &T iprintExp(&T v) {
	iprint(v);
	return v;
}

@doc{
Synopsis: Print an indented representation of a value followed by a newline and returns the value as result.

Description:
See [$IO/iprintExp] for a version that does not add a newline.

Examples:

<screen>
import IO;
iprintlnExp(["fruits", ("spider" : 8, "snake" : 0), [10, 20, 30]]);
</screen>
}
public &T iprintlnExp(&T v) {
	iprintln(v);
	return v;
}


@doc{
Synopsis: Print a indented representation of a value and add a newline at the end.

Description:
See [$IO/iprintlnExp] for a version that returns its argument as result
and [$IO/iprint] for a version that does not add a newline.

Examples:

<screen>
import IO;
iprintln(["fruits", ("spider" : 8, "snake" : 0), [10, 20, 30]]);
</screen>
}
@reflect{for getting IO streams}
@javaClass{org.rascalmpl.library.Prelude}
public java void iprintln(value arg); 

@doc{
Synopsis: Check whether a given location is actually a file (and not a directory).

Description:
Check whether location `file` is actually a file.
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java bool isFile(loc file);


@doc{
Synopsis: Last modification date of a location.

Description:
Returns last modification time of the file at location `file`.

Examples:
<screen>
import IO;
// Determine the last modification date of the Rascal standard library:
lastModified(|std:///IO.rsc|);
</screen>
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java datetime lastModified(loc file);

@doc{
Synopsis: List the entries in a directory.

Description:
List the entries in directory `file`.

Examples:

<screen errors>
import IO;
// List all entries in the standard library:
listEntries(|std:///|);
</screen>
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java list[str] listEntries(loc file);


@doc{
Synopsis: Create a new directory.

Description:
Create a directory at location `file`.
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java void mkDirectory(loc file)
throws PathNotFound(loc file), IO(str msg);

@doc{
Synopsis: Print a value without subsequent newline.

Description:
Print a value on the output stream.
See [$IO/println] for a version that adds a newline
and [$IO/printExp] for a version that returns its argument as value.


Examples:

Note that the only difference with [println] is that no newline is added after the value is printed
(note where the prompt `ok` appears):
<screen>
import IO;
print("Hello World");
</screen>
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{for getting IO streams}
public java void print(value arg);

@doc{
Synopsis: Print a value and return it as result.

Examples:
<screen>
import IO;
printExp(3.14);
printExp("The value of PI is approximately ", 3.14);
</screen>
}
public &T printExp(&T v) {
	print("<v>");
	return v;
}

public &T printExp(str msg, &T v) {
	print("<msg><v>");
	return v;
}

@doc{
Synopsis: Print a value to the output stream and add a newline.

Description:
Print a value on the output stream followed by a newline.
See [$IO/print] for a version that does not add a newline
and [$IO/printlnExp] for a version that returns its argument as value.

Examples:
<screen>
import IO;
println("Hello World");
// Introduce variable S and print it:
S = "Hello World";
println(S);
// Introduce variable L and print it:
L = ["a", "b", "c"];
println(L);
// Use a string template to print several values:
println("<S>: <L>");
// Just print a newline
println();
</screen>
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{for getting IO streams}
public java void println(value arg);

@javaClass{org.rascalmpl.library.Prelude}
@reflect{for getting IO streams}
public java void println();

@doc{
Synopsis: Print a value followed by a newline and return it as result.

Examples:
<screen>
import IO;
printlnExp(3.14);
printlnExp("The value of PI is approximately ", 3.14);
</screen>
}
public &T printlnExp(&T v) {
	println("<v>");
	return v;
}

public &T printlnExp(str msg, &T v) {
	println("<msg><v>");
	return v;
}

@doc{
Synopsis: Raw print of a value.

Description: Raw print of a value on the output stream, does not convert parse trees or remove quotes from strings.


Pitfalls:
This function is only available for internal use in the Rascal development team.
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{for getting IO streams}
public java void rprint(value arg);

    
@doc{
Synopsis: Raw print of a value followed by newline.

Description: Raw print of a value on the output stream followed by newline, does not convert parse trees or remove quotes from strings.

Pitfalls:
This function is only available for internal use in the Rascal development team.
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{for getting IO streams}
public java void rprintln(value arg);

@doc{
Synopsis: Read the contents of a location and return it as string value.

Description:
Return the contents of a file location as a single string.
Also see [readFileLines].

== Encoding ==
A text file can be encoded in many different character sets, most common are UTF8, ISO-8859-1, and ASCII.
If you know the encoding of the file, please use the [readFileEnc] and [readFileLinesEnc] overloads.
If you do not know, we try to detect this. This detection shall be explained below:

# Does the scheme of the [Location] (eg. `|project:///|`) define the charset of the file? __Use the provided charset__
# Does the file contain a UTF8/16/32 [BOM](http://en.wikipedia.org/wiki/Byte_order_mark)? __Use the charset matching the BOM__
# Try to use heuristics to determine if our default fallbacks can match:
  ## Are the first 32 bytes valid UTF-8? __Use UTF-8__
  ## Are the first 32 bytes valid UTF-32? __Use UTF-32__
# Fallback to the system default

__To summarize__, we use UTF-8 by default, except if the [Location] has available meta-data, the file contains a BOM, or
the first 32 bytes of the file are not valid UTF-8.

Pitfalls:
* The second version of `readFile` with a string argument is __deprecated__.
* In case encoding is not known, we try to estimate as best as we can.
* We default to UTF-8, if the file was not encoded in UTF-8 but the first characters were valid UTF-8, 
  you might get an decoding error or just strange looking characters.


}

@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java str readFile(loc file)
throws PathNotFound(loc file), IO(str msg);

@doc{
Synopsis: Read the contents of a location and return it as string value.

Description:
Return the contents (decoded using the Character set supplied) of a file location as a single string.
Also see [readFileLinesEnc].
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java str readFileEnc(loc file, str charset)
throws PathNotFound(loc file), IO(str msg);

@deprecated{Use @see str readFile(loc file)}
@javaClass{org.rascalmpl.library.Prelude}
public java list[str] readFile(str filename)
throws IO(str msg);

@doc{
Synopsis: Read the contents of a file and return it as a list of bytes.
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java list[int] readFileBytes(loc file)
throws PathNotFound(loc file), IO(str msg);


@doc{
Synopsis: Read the contents of a file location and return it as a list of strings.

Description:
Return the contents of a file location as a list of lines.
Also see [readFile].

== Encoding ==
Look at [readFile] to understand how this function chooses the character set. If you know the character set used, please use [readFileLinesEnc].

Pitfalls:
* In case encoding is not known, we try to estimate as best as we can (see [readFile]).
* We default to UTF-8, if the file was not encoded in UTF-8 but the first characters were valid UTF-8, 
  you might get an decoding error or just strange looking characters (see [readFile]).
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java list[str] readFileLines(loc file)
throws PathNotFound(loc file), IO(str msg);

@doc{
Synopsis: Read the contents of a file location and return it as a list of strings.

Description:
Return the contents (decoded using the Character set supplied) of a file location as a list of lines.
Also see [readFileLines].
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java list[str] readFileLinesEnc(loc file, str charset)
throws PathNotFound(loc file), IO(str msg);

@doc{
Synopsis: Write values to a file.

Description:
Write a textual representation of some values to a file:
* If a value is a simple string, the quotes are removed and the contents are de-escaped.
* If a value has a non-terminal type, the parse tree is unparsed to produce a value.
* All other values are printed as-is.
* Each value is terminated by a newline character.

Files are encoded in UTF-8, in case this is not desired, use [writeFileEnc].
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java void writeFile(loc file, value V...)
throws PathNotFound(loc file), IO(str msg);

@doc{
Synopsis: Write values to a file.

Description:
Write a textual representation of some values to a file:
* If a value is a simple string, the quotes are removed and the contents are de-escaped.
* If a value has a non-terminal type, the parse tree is unparsed to produce a value.
* All other values are printed as-is.
* Each value is terminated by a newline character.

Files are encoded using the charset provided.
}
@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java void writeFileEnc(loc file, str charset, value V...)
throws PathNotFound(loc file), IO(str msg);

@doc{
Synopsis: Changes the last modification date of a file.
}
public void touch(loc file)
throws PathNotFound(loc file), IO(str msg){
  appendToFile(file);
}

@doc{
Synopsis: Read the contents of a location and return its MD5 hash.

Description:
MD5 hash the contents of a file location.
}

@javaClass{org.rascalmpl.library.Prelude}
@reflect{Uses URI Resolver Registry}
public java str md5HashFile(loc file)
throws PathNotFound(loc file), IO(str msg);

@javaClass{org.rascalmpl.library.Prelude}
public java str createLink(str title, str target);
