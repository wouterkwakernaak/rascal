module LAPD

@javaClass{org.rascalmpl.library.LAPD}
public java void write(str id, value v);

@javaClass{org.rascalmpl.library.LAPD}
@reflect{uses typestore of the root environment}
public java &T read(str id, type[&T] t);

@javaClass{org.rascalmpl.library.LAPD}
@reflect{uses typestore of the root environment}
public java value read(str id);

@javaClass{org.rascalmpl.library.LAPD}
@reflect{uses typestore of the root environment}
public java &T executeQuery(str query, type[&T] t, bool isCollection);

@javaClass{org.rascalmpl.library.LAPD}
@reflect{uses typestore of the root environment}
public java &T executeJavaQuery(int queryId, str graphId, type[&T] t);

@javaClass{org.rascalmpl.library.LAPD}
public java str generateUniqueId();

@javaClass{org.rascalmpl.library.LAPD}
public java loc getDbDirectoryPath();