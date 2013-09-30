package org.rascalmpl.library.experiments.Compiler.RVM.Interpreter;

import java.util.List;

import org.eclipse.imp.pdb.facts.IInteger;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeFactory;
import org.eclipse.imp.pdb.facts.type.TypeStore;
import org.rascalmpl.values.ValueFactoryFactory;

public class RuntimeExceptions {
	
	private static TypeFactory TF = TypeFactory.getInstance();
	private static IValueFactory VF = ValueFactoryFactory.getValueFactory();
	
	public static final TypeStore TS = new TypeStore();
	public static final Type Exception = TF.abstractDataType(TS, "RuntimeException");
	
	public static final Type StackOverflow = TF.constructor(TS, Exception, "StackOverflow");
    public static final Type IndexOutOfBounds = TF.constructor(TS, Exception, "IndexOutOfBounds", TF.integerType(), "index");
	public static final Type AssertionFailed = TF.constructor(TS,Exception,"AssertionFailed");
	public static final Type LabeledAssertionFailed = TF.constructor(TS,Exception,"AssertionFailed", TF.stringType(), "label");
	public static final Type EmptyList = TF.constructor(TS,Exception,"EmptyList");
	public static final Type EmptySet = TF.constructor(TS,Exception,"EmptySet");
	public static final Type EmptyMap = TF.constructor(TS,Exception,"EmptyMap");
	public static final Type NoSuchElement = TF.constructor(TS,Exception,"NoSuchElement",TF.valueType(), "v");
	public static final Type UnavailableInformation = TF.constructor(TS,Exception, "UnavailableInformation");
	public static final Type IllegalArgument = TF.constructor(TS,Exception,"IllegalArgument",TF.valueType(), "v", TF.stringType(), "message");
	public static final Type IllegalTypeArgument = TF.constructor(TS,Exception,"IllegalTypeArgument",TF.stringType(), "type", TF.stringType(), "message");

	public static final Type AnonymousIllegalArgument = TF.constructor(TS,Exception,"IllegalArgument");
	public static final Type IO = TF.constructor(TS,Exception,"IO",TF.stringType(), "message");
	public static final Type PathNotFound = TF.constructor(TS,Exception,"PathNotFound",TF.sourceLocationType(), "location");
	
	public static final Type LocationNotFound = TF.constructor(TS,Exception,"LocationNotFound",TF.sourceLocationType(), "location");
	public static final Type PermissionDenied = TF.constructor(TS,Exception,"PermissionDenied",TF.stringType(), "message");
	public static final Type AnonymousPermissionDenied = TF.constructor(TS,Exception,"PermissionDenied");
	public static final Type ModuleNotFound = TF.constructor(TS, Exception, "ModuleNotFound", TF.stringType(), "name");
	public static final Type MultipleKey = TF.constructor(TS, Exception, "MultipleKey", TF.valueType(), "key");
	public static final Type NoSuchKey = TF.constructor(TS, Exception, "NoSuchKey", TF.valueType(), "key");
	public static final Type NoSuchAnnotation = TF.constructor(TS, Exception, "NoSuchAnnotation", TF.stringType(), "label");
	public static final Type NoSuchField = TF.constructor(TS, Exception, "NoSuchField", TF.stringType(), "label");
	public static final Type ParseError = TF.constructor(TS, Exception, "ParseError", TF.sourceLocationType(), "location");
	public static final Type IllegalIdentifier = TF.constructor(TS, Exception, "IllegalIdentifier", TF.stringType(), "name");
	public static final Type IllegalChar = TF.constructor(TS, Exception, "IllegalCharacter", TF.integerType(), "character");
	public static final Type SchemeNotSupported = TF.constructor(TS, Exception, "SchemeNotSupported", TF.sourceLocationType(), "location");
	public static final Type MalFormedURI = TF.constructor(TS, Exception, "MalFormedURI", TF.stringType(), "malFormedUri");
	public static final Type NoParent = TF.constructor(TS, Exception, "NoParent", TF.sourceLocationType(), "noParentUri");
	public static final Type NameMismatch = TF.constructor(TS, Exception, "NameMismatch", TF.stringType(), "expectedName", TF.stringType(), "gotName");
	public static final Type ArityMismatch = TF.constructor(TS, Exception, "ArityMismatch", TF.integerType(), "expectedArity", TF.integerType(), "gotArity");

	public static final Type Java = TF.constructor(TS, Exception, "Java", TF.stringType(), "class", TF.stringType(), "message");
	public static final Type JavaWithCause = TF.constructor(TS, Exception, "Java", TF.stringType(), "class", TF.stringType(), "message", Exception, "cause");
  
	public static final Type Subversion = TF.constructor(TS, Exception, "Subversion", TF.stringType(), "message");
	public static final Type JavaBytecodeError = TF.constructor(TS, Exception, "JavaBytecodeError", TF.stringType(), "message");

	public static final Type InvalidUseOfDate = TF.constructor(TS, Exception, "InvalidUseOfDate", TF.dateTimeType(), "msg");
	public static final Type InvalidUseOfTime = TF.constructor(TS, Exception, "InvalidUseOfTime", TF.dateTimeType(), "msg");
	public static final Type InvalidUseOfDateTime = TF.constructor(TS, Exception, "InvalidUseOfDateTime", TF.dateTimeType(), "msg");
	public static final Type InvalidUseOfLocation = TF.constructor(TS, Exception, "InvalidUseOfLocation", TF.stringType(), "message");
	public static final Type DateTimeParsingError = TF.constructor(TS, Exception, "DateTimeParsingError", TF.stringType(), "message");
	public static final Type DateTimePrintingError = TF.constructor(TS, Exception, "DateTimePrintingError", TF.stringType(), "message");
	public static final Type Timeout = TF.constructor(TS, Exception, "Timeout");
	public static final Type Figure = TF.constructor(TS, Exception, "Figure", TF.stringType(), "message", TF.valueType(), "figure");
	
	public static final Type ImplodeError = TF.constructor(TS, Exception, "ImplodeError", TF.stringType(), "message");

	public static final Type ArithmeticException = TF.constructor(TS, Exception, "ArithmeticException", TF.stringType(), "message");

	
	public static Thrown arithmeticException(String msg, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(ArithmeticException, VF.string(msg)), loc, stacktrace);
	}
	
	public static Thrown assertionFailed(ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(AssertionFailed), loc, stacktrace);
	}

	public static Thrown assertionFailed(IString msg, ISourceLocation loc, List<Frame> stacktrace) {
    	return Thrown.getInstance(VF.constructor(LabeledAssertionFailed, msg), loc, stacktrace);
    }
	
	public static Thrown emptyList(ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(EmptyList), loc, stacktrace);
	}
	
	public static Thrown emptySet(ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(EmptySet), loc, stacktrace);
	}
	
	public static Thrown emptyMap(ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(EmptyMap), loc, stacktrace);
	}
	
	public static Thrown illegalArgument(ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(AnonymousIllegalArgument), loc, stacktrace);	
	}
	
	public static Thrown illegalArgument(IValue v, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(IllegalArgument), loc, stacktrace);	
	}
	
	public static Thrown illegalArgument(IValue v, ISourceLocation loc, List<Frame> stacktrace, String message) {
		return Thrown.getInstance(VF.constructor(IllegalArgument, v, VF.string(message)), loc, stacktrace);	
	}
	
	public static Thrown indexOutOfBounds(IInteger i, ISourceLocation loc, List<Frame> stacktrace) {
    	return Thrown.getInstance(VF.constructor(IndexOutOfBounds, i), loc, stacktrace);
    }
	
	public static Thrown io(IString msg, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(IO, msg), loc, stacktrace);
	}
	
//	private static Thrown javaException(String clazz, String message, IValue cause, ISourceLocation loc, List<Frame> stacktrace) {
//		return Thrown.getInstance(VF.constructor(Java, VF.string(clazz), VF.string(message), cause), loc, stacktrace);
//	}
//
//	private static Thrown javaException(String clazz, String message, ISourceLocation loc, List<Frame> stacktrace) {
//		return Thrown.getInstance(VF.constructor(Java, VF.string(clazz), VF.string(message)), loc, stacktrace);
//	}

	// TODO: java exceptions
	
	public static Thrown moduleNotFound(IString module, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(ModuleNotFound, module), loc, stacktrace);
	}
	
	public static Thrown noSuchAnnotation(String label, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(NoSuchAnnotation, VF.string(label)), loc, stacktrace);
	}

	public static Thrown noSuchKey(IValue v, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(NoSuchKey, v), loc, stacktrace);
	}
	
	public static Thrown parseError(ISourceLocation parseloc, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(ParseError, parseloc), loc, stacktrace);
	}
	
	public static Thrown pathNotFound(ISourceLocation parseloc, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(PathNotFound, parseloc), loc, stacktrace);
	}
	
	public static Thrown stackOverflow(ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(StackOverflow), loc, stacktrace);
	}
	
	public static Thrown arityMismatch(int expected, int got, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(ArityMismatch, VF.integer(expected), VF.integer(got)), loc, stacktrace);
	}
	
	public static Thrown dateTimeParsingError(String message, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(DateTimeParsingError, VF.string(message)), loc, stacktrace);
	}
	
	public static Thrown dateTimePrintingError(String message, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(DateTimePrintingError, VF.string(message)), loc, stacktrace);
	}	
	
	public static Thrown figureException(String message, IValue v, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(Figure, VF.string(message), v), loc, stacktrace);
	}
	
	public static Thrown illegalCharacter(IInteger i, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(IllegalChar, i), loc, stacktrace);
	}
	
	public static Thrown illegalTypeArgument(String type, String message, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(IllegalTypeArgument,VF.string(type),VF.string(message)), loc, stacktrace);	
	}
	
	public static Thrown illegalTypeArgument(String type, ISourceLocation loc, List<Frame> stacktrace){
		return Thrown.getInstance(VF.constructor(IllegalTypeArgument,VF.string(type)), loc, stacktrace);	
	}
	
	public static Thrown implodeError(String msg, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(ImplodeError, VF.string(msg)), loc, stacktrace);
	}

	public static Thrown invalidUseOfLocation(String msg, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(InvalidUseOfLocation, VF.string(msg)), loc, stacktrace);
	}	
	
	public static Thrown invalidUseOfDateException(String message, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(InvalidUseOfDate, VF.string(message)), loc, stacktrace);
	}
	
	public static Thrown invalidUseOfTimeException(String message, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(InvalidUseOfTime, VF.string(message)), loc, stacktrace);
	}
	
	public static Thrown invalidUseOfDateTimeException(String message, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(InvalidUseOfDateTime, VF.string(message)), loc, stacktrace);
	}
	
	public static Thrown malformedURI(String uri, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(MalFormedURI, VF.string(uri)), loc, stacktrace);
	}
	
	public static Thrown MultipleKey(IValue v, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(MultipleKey, v), loc, stacktrace);
	}
	
	public static Thrown nameMismatch(String expected, String got, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(NameMismatch, VF.string(expected), VF.string(got)), loc, stacktrace);
	}
	
	public static Thrown noParent(ISourceLocation noparentloc, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(NoParent, noparentloc), loc, stacktrace);
	}
	
	public static Thrown noSuchElement(IValue v, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(NoSuchElement,v), loc, stacktrace);	
	}

	public static Thrown noSuchField(String name, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(NoSuchField, VF.string(name)), loc, stacktrace);
	}
	
	public static Thrown permissionDenied(ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(AnonymousPermissionDenied), loc, stacktrace);
	}
	
	public static Thrown permissionDenied(IString msg, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(PermissionDenied, msg), loc, stacktrace);
	}

	public static Thrown unavailableInformation(ISourceLocation loc, List<Frame> stacktrace){
		return Thrown.getInstance(VF.constructor(UnavailableInformation), loc, stacktrace);	
	}

	public static Thrown schemeNotSupported(ISourceLocation file, ISourceLocation loc, List<Frame> stacktrace) {
		return Thrown.getInstance(VF.constructor(SchemeNotSupported, file), loc, stacktrace);
	}
	
	public static Thrown timeout(ISourceLocation loc, List<Frame> stacktrace) {
    	return Thrown.getInstance(VF.constructor(Timeout), loc, stacktrace);
    }

}
