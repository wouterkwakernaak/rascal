module experiments::Compiler::Tests::Patterns

extend experiments::Compiler::Tests::TestUtils;

// Literals

// Boolean

test bool tst() = run("true := true") == true := true;
test bool tst() = run("true := false") == true := false;

// Integer

test bool tst() = run("1 := 1") == 1 := 1;
test bool tst() = run("1 := 2") == 1 := 2;

// Real
test bool tst() = run("2.3 := 2.3") == (2.3 := 2.3);
test bool tst() = run("2.5 := 2.3") == (2.5 := 2.3);


// Rational
test bool tst() = run("2r3 := 2r3") == (2r3 := 2r3);
test bool tst() = run("2r5 := 2r3") == (2r5 := 2r3);

// String

test bool tst() = run("\"a\" := \"a\"") == "a" := "a";
test bool tst() = run("\"a\" := \"b\"") == "a" := "b";

// Datetime
// The following two tests fail, since the interpreter does not support datetime patterns. We are ahead :-)
/*fails*/ //test bool tst() = run("$2012-01-01T08:15:30.055+0100$ := $2012-01-01T08:15:30.055+0100$") == ($2012-01-01T08:15:30.055+0100$ := $2012-01-01T08:15:30.055+0100$);
/*fails*/ //test bool tst() = run("$2013-01-01T08:15:30.055+0100$ := $2012-01-01T08:15:30.055+0100$") == ($2013-01-01T08:15:30.055+0100$ := $2012-01-01T08:15:30.055+0100$);


// Location

test bool tst() = run("|http://www.rascal-mpl.org| := |http://www.rascal-mpl.org|") == (|http://www.rascal-mpl.org| == |http://www.rascal-mpl.org|);
test bool tst() = run("|http://www.rascal-mpl.org| := |std://demo/basic/Hello.rsc|") == (|http://www.rascal-mpl.org| == |std://demo/basic/Hello.rsc|);

// Basic Patterns

test bool tst() = run("x := 2") == x := 2;
test bool tst() = run("_ := 2") == _ := 2;

test bool tst() = run("x := 2") == x := 2 && x == 2;

test bool tst() = run("int x := 2") == int x := 2;
test bool tst() = run("int _ := 2") == int _ := 2;
test bool tst() = run("int x := 2") == int x := 2 && x == 2;

test bool tst() = run("x:1 := 1") == x:1 := 1;
test bool tst() = run("x:1 := 1") == x:1 := 1 && x == 1;

test bool tst() = run("int x:1 := 1") == int x:1 := 1;
test bool tst() = run("int x:1 := 1") == int x:1 := 1 && x == 1;

test bool tst() = run("[int] 1 := 1") == [int] 1 := 1;

test bool tst() = run("! 1 := 2") == ! 1 := 2;
test bool tst() = run("! 1 := 1") == ! 1 := 1;

// List matching
test bool tst() = run("[1] := [1]") == [1] := [1];
test bool tst() = run("[1] := [2]") == [1] := [2];
test bool tst() = run("[1] := [1,2]") == [1] := [1,2];

test bool tst() = run("[1, x*, 5] := [1,2,3,4,5]") == [1, x*, 5] := [1,2,3,4,5];
test bool tst() = run("[1, _*, 5] := [1,2,3,4,5]") == [1, _*, 5] := [1,2,3,4,5];
test bool tst() = run("[1, x*, 5] := [1,2,3,4,5]") == [1, x*, 5] := [1,2,3,4,5] && x == [2,3,4];

test bool tst() = run("[1, *x, 5] := [1,2,3,4,5]") == [1, *x, 5] := [1,2,3,4,5];
test bool tst() = run("[1, *x, 5] := [1,2,3,4,5]") == [1, *x, 5] := [1,2,3,4,5] && x == [2,3,4];

test bool tst() = run("[1, *int x, 5] := [1,2,3,4,5]") == [1, *int x, 5] := [1,2,3,4,5];
test bool tst() = run("[1, *int _, 5] := [1,2,3,4,5]") == [1, *int _, 5] := [1,2,3,4,5];
test bool tst() = run("[1, *int x, 5] := [1,2,3,4,5]") == [1, *int x, 5] := [1,2,3,4,5] && x == [2,3,4];

test bool tst() = run("[str _, *int _] := [\"a\", 1, 2]") == [str _, *int _] := ["a", 1, 2];
test bool tst() = run("[str _, *int _, *value _] := [\"a\", 1, 2, \"b\"]") == [str _, *int _,*value _] := ["a", 1, 2, "b"];

test bool tst() = run("{ res = for([*x,*y,*z] := [1,2,3]) append \<x,y,z\>; res; }") == { res = for([*x,*y,*z] := [1,2,3]) append <x,y,z>; res; };
test bool tst() = run("{ res = for([*x,*y,*int z] := [1,2,3]) append \<x,y,z\>; res; }") == { res = for([*x,*y,*int z] := [1,2,3]) append <x,y,z>; res; };
test bool tst() = run("{ res = for([*x,*int y,*int z] := [1,2,3]) append \<x,y,z\>; res; }") == { res = for([*x,*int y,*int z] := [1,2,3]) append <x,y,z>; res; };
test bool tst() = run("{ res = for([*int x,*int y,*int z] := [1,2,3]) append \<x,y,z\>; res; }") == { res = for([*int x,*int y,*int z] := [1,2,3]) append <x,y,z>; res; };


// Set matching

test bool tst() = run("{1} := {1}") == {1} := {1};
test bool tst() = run("{1} := {2}") == {1} := {2};
test bool tst() = run("{1, 2} := {2, 1}") == {1, 2} := {2, 1};
test bool tst() = run("{1} := {1,2}") == {1} := {1,2};

test bool tst() = run("{x, 2} := {2, 1}") == {x, 2} := {2, 1};

test bool tst() = run("{1, x*, 5} := {1,2,3,4,5}") == {1, x*, 5} := {1,2,3,4,5};
test bool tst() = run("{1, _*, 5} := {1,2,3,4,5}") == {1, _*, 5} := {1,2,3,4,5};
test bool tst() = run("{1, x*, 5} := {1,2,3,4,5} && x == {2, 3, 4}") == {1, x*, 5} := {1,2,3,4,5} && x == {2,3,4};

test bool tst() = run("{1, *int x, 5} := {1,2,3,4,5}") == {1, *int x, 5} := {1,2,3,4,5};
test bool tst() = run("{1, *int _, 5} := {1,2,3,4,5}") == {1, *int _, 5} := {1,2,3,4,5};
test bool tst() = run("{1, *int x, 5} := {1,2,3,4,5} && x == {2, 3, 4}") == {1, *int x, 5} := {1,2,3,4,5} && x == {2,3,4};

test bool tst() = run("{ y = {5, 6}; {*int x, 3, *y} := {1,2,3,4,5,6};}") == { y = {5, 6}; {*int x, 3, *y} := {1,2,3,4,5,6};};

test bool tst() = run("{*_} := {}") == {*_} := {};
test bool tst() = run("!{*_} := {}") == !{*_} := {};
test bool tst() = run("{*_,1} := {}") == {*_,1} := {};

// Following two failures due to error in Rascal interpreter:
/*fails*/ // test bool tst() = run("!{*_,1} := {}") == !{*_,1} := {};
test bool tst() = run("{*_,1} := {2}") == {*_,1} := {2};
/*fails*/ //test bool tst() = run("!{*_,1} := {2}") == !{*_,1} := {2};

test bool tst() = run("{str _, *int _} := {\"a\", 1, 2}") == {str _, *int _} := {"a", 1, 2};
test bool tst() = run("{str _, *int _, *value _} := {\"a\", 1, 2, \"b\"}") == {str _, *int _,*value _} := {"a", 1, 2, "b"};
test bool tst() = run("{for({str S, *int N, *value V} := {\"a\", 1, 2, \"b\"}){ append \<S,N,V\>;}}") == {for({str S, *int N, *value V} := {"a", 1, 2, "b"}){ append <S,N,V>;}};

test bool tst() = run("{ res = for({*x,*y, *z} := {1,2,3}) append \<x,y,z\>; res; }") == { res = for({*x,*y,*z} := {1,2,3}) append <x,y,z>; res; };
test bool tst() = run("{ res = for({*x,*y, *int z} := {1,2,3}) append \<x,y,z\>; res; }") == { res = for({*x,*y,*int z} := {1,2,3}) append <x,y,z>; res; };
test bool tst() = run("{ res = for({*x,*int y, *int z} := {1,2,3}) append \<x,y,z\>; res; }") == { res = for({*x,*int y,*int z} := {1,2,3}) append <x,y,z>; res; };
test bool tst() = run("{ res = for({*int x,*int y, *int z} := {1,2,3}) append \<x,y,z\>; res; }") == { res = for({*int x,*int y,*int z} := {1,2,3}) append <x,y,z>; res; };


// Node/Constructor matching

test bool tst() = run("d1(1,\"a\") := d1(1, \"a\")") == d1(1,"a") := d1(1, "a");
test bool tst() = run("d1(1,\"a\") := d1(2,\"a\")") == d1(1,"a") := d1(2,"a");
test bool tst() = run("d2(\"a\", true) := d2(\"a\", true)") == d2("a", true) := d2("a", true);
test bool tst() = run("d2(\"a\", true) := d2(\"b\", true)") == d2("a", true) := d2("b", true);

test bool tst() = run("d1(x, \"a\") := d1(1, \"a\")") == d1(x, "a") := d1(1, "a") && x == 1;
test bool tst() = run("d1(int x, \"a\") := d1(1, \"a\")") == d1(int x, "a") := d1(1, "a") && x == 1;

test bool tst() = run("str f(int x, str s) := d1(1, \"a\")") == str f(int x, str s) := d1(1, "a") && x == 1 && s == "a" && f == "d1";

test bool tst() = run("{ s = \"not matched\"; switch(\"a\"(1,2)) { case node nd: str n(int x, int y): s = n + \"_matched(\<x\>,\<y\>)\"; } s; }") 
				    == { s = "not matched";   switch("a"(1,2))   { case node nd: str n(int x, int y): s = n + "_matched(<x>,<y>)"; } s; };

test bool tst() = run("{ s = \"not matched\"; switch(\"a\"(1,2)) { case nd: str n(x,y): s = n + \"_matched(\<x\>,\<y\>)\"; } s; }") 
				    == { s = "not matched";   switch("a"(1,2))   { case nd: str n(x,y): s = n + "_matched(<x>,<y>)"; } s; };

// Descendant matching

test bool tst() = run("/1 := [[1, 2], [5, [8], 7], \"a\"]") == /1 := [[1, 2], [5, [8], 7], "a"];
test bool tst() = run("/2 := [[1, 2], [5, [8], 7], \"a\"]") == /2 := [[1, 2], [5, [8], 7], "a"];
test bool tst() = run("/8 := [[1, 2], [5, [8], 7], \"a\"]") == /8 := [[1, 2], [5, [8], 7], "a"];
test bool tst() = run("/10 := [[1, 2], [5, [8], 7], \"a\"]") == /10 := [[1, 2], [5, [8], 7], "a"];

test bool tst() = run("/1 := d1(1, \"a\")") == /1 := d1(1, "a");
test bool tst() = run("/1 := d1(2, \"a\")") == /1 := d1(2, "a");

test bool tst() = run("/1 := [10, d1(1, \"a\"), 11]") == /1 := [10, d1(1, "a"), 11];
test bool tst() = run("/1 := [10, d1(2, \"a\"), 11]") == /1 := [10, d1(2, "a"), 11];

test bool tst() = run("/1 := {10, d1(1, \"a\"), 11}") == /1 := {10, d1(1, "a"), 11};
test bool tst() = run("/1 := {10, d1(2, \"a\"), 11}") == /1 := {10, d1(2, "a"), 11};

test bool tst() = run("/1 := \<10, d1(1, \"a\"), 11\>") == /1 := <10, d1(1, "a"), 11>;
test bool tst() = run("/1 := \<10, d1(2, \"a\"), 11\>") == /1 := <10, d1(2, "a"), 11>;

test bool tst() = run("/3 := (1 :10, 2:20, 3 :30)") == /3 := (1 :10, 2:20, 3 :30);
test bool tst() = run("/4 := (1 :10, 2:20, 3 :30)") == /3 := (1 :10, 2:20, 4 :30);


test bool tst() = run("/300 := (1 :[10, 100], 2:[20,200], 3 :[30,300])") == /300 := (1 :[10, 100], 2:[20,200], 3 :[30,300]);
test bool tst() = run("/400 := (1 :[10, 100], 2:[20,200], 3 :[30,300])") == /400 := (1 :[10, 100], 2:[20,200], 3 :[30,300]);


test bool tst() = run("/int x := d1(1, \"a\") && x == 1") == (/int x := d1(1, "a") && x == 1);

// Regular expressions

test bool tst() = run("/a/ := \"a\"") == (/a/ := "a");
test bool tst() = run("/a/i := \"A\"") == (/a/i := "A");
test bool tst() = run("/a/d := \"a\"") == (/a/d := "a");
test bool tst() = run("/a/m := \"a\"") == (/a/m := "a");
test bool tst() = run("/a\nb/ := \"a\nb\"") == (/a\nb/ := "a\nb");
test bool tst() = run("/a.b/ := \"a\nb\"") == (/a.b/ := "a\nb");
test bool tst() = run("/a.b/d := \"a\nb\"") == (/a.b/d := "a\nb");

// NoMatch

test bool tst() = run("1 !:= 1") == 1 !:= 1;
test bool tst() = run("1 !:= 2") == 1 !:= 2;

test bool tst() = run("int x !:= 2") == int x !:= 2;

test bool tst() = run("[1, x*, 5] !:= [1,2,3,4,5]") == [1, x*, 5] !:= [1,2,3,4,5];
test bool tst() = run("[1, x*, 5] !:= [1,2,3,4,6]") == [1, x*, 5] !:= [1,2,3,4,6];

// False match as the subject dynamic type is not a subtype of the pattern type
test bool tst() = run("{ value v = { [1,2] }; [1,2] := v; }") == { value v = { [1,2] }; [1,2] := v; };
test bool tst() = run("{ value v = \<1,2\>; {1,2} := v; }") == { value v = <1,2>; {1,2} := v; };
test bool tst() = run("{ value v = \<1,2\>; \"nd\"(1,2) := v; }") == { value v = <1,2>; "nd"(1,2) := v; };


// Variables defined outside a pattern 

test bool tst() = run("{int x = 2; x := 2;}") == {int x = 2; x := 2;};
test bool tst() = run("{int x = 3; x := 2;}") == {int x = 3; x := 2;};
test bool tst() = run("{int x; x := 2;}") == {int x; x := 2;};

// Here x should actually be undefined
/*fails*/ // test bool tst() = run("{int x; x := 2; x;}") == {int x; x := 2; x;};

test bool tst() = run("{int x = 3; [1, x] := [1,3];}") == {int x = 3; [1, x] := [1,3];};
test bool tst() = run("{int x = 7; [1, x] := [1,3];}") == {int x = 7; [1, x] := [1,3];};

test bool tst() = run("{list[int] x = [10,20]; [1, x*] := [1,10,20];}") == {list[int] x = [10,20]; [1, x*] := [1,10,20];};
test bool tst() = run("{list[int] x = [10,20]; [1, x*] := [1,10,70];}") == {list[int] x = [10,20]; [1, x*] := [1,10,70];};

test bool tst() = run("{int x = 3; {1, x} := {1,3};}") == {int x = 3; {1, x} := {1,3};};
test bool tst() = run("{int x = 7; {1, x} := {1,3};}") == {int x = 7; {1, x} := {1,3};};

test bool tst() = run("{set[int] x = {10,20}; {1, x*} := {1,10,20};}") == {set[int] x = {10,20}; {1, x*} := {1,10,20};};
test bool tst() = run("{set[int] x = {10,20}; {1, x*} := {1,10,70};}") == {set[int] x = {10,20}; {1, x*} := {1,10,70};};


// non-linear patterns

test bool tst() = run("\<x, x\> := \<1, 1\>") == <x, x> := <1, 1>;
test bool tst() = run("\<x, x\> := \<1, 2\>") == <x, x> := <1, 2>;

test bool tst() = run("[*int x, 3, *x] := [1,2,3,1,2]") == [*int x, 3, x] := [1,2,3,1,2] && x == [1, 2];
test bool tst() = run("[*int x, 3, *x] := [1,2,3,1,2] && x == [1, 2]") == [*int x, 3, x] := [1,2,3,1,2] && x == [1, 2];

test bool tst() = run("[*int x, *x, 3] := [1,2,1,2,3] && x == [1, 2]") == [*int x, *x, 3] := [1,2,1,2, 3] && x == [1, 2];
test bool tst() = run("[*int x, *x, 3] := [1,2,3,1,2]") == [*int x, *x, 3] := [1,2,3,1,2];

// Note: non-lineair set patterns are a bit weird, what to do about them?

// Variable interaction i Boolean expressions

test bool tst() = run("[x, 2] := [1,2] || [x, 3] := [1, 3]") == [x, 2] := [1,2] || [x, 3] := [1, 3];
test bool tst() = run("{int x = 1; [x, 2] := [10,2] || [x, 3] := [1, 3];}") == {int x = 1; [x, 2] := [10,2] || [x, 3] := [1, 3];};
test bool tst() = run("{int x = 1; [x, 2] := [10,2] || [x, 3] := [20, 3];}") == {int x = 1; [x, 2] := [10,2] || [x, 3] := [20, 3];};

test bool tst() = run("[x, 2] := [10,2] && [x, 3] := [10, 3]") == [x, 2] := [10,2] && [x, 3] := [10, 3];


