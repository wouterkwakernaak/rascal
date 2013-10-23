module LAPD

@javaClass{org.rascalmpl.library.LAPD}
public java void write(str id, value v);

@javaClass{org.rascalmpl.library.LAPD}
public java &T read(str id, type[&T] t);

@javaClass{org.rascalmpl.library.LAPD}
public java str generateUniqueId();

@javaClass{org.rascalmpl.library.LAPD}
public java loc getDbDirectoryPath();