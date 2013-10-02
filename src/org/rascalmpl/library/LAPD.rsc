module LAPD

@javaClass{org.rascalmpl.library.LAPD}
public java void write(str id, value v);

@javaClass{org.rascalmpl.library.LAPD}
public java &T read(str id, type[&T] v);

@javaClass{org.rascalmpl.library.LAPD}
public java str generateRandomId();