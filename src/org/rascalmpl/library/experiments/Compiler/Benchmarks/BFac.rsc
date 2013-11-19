module experiments::Compiler::Benchmarks::BFac

int fac(int n) = (n <= 0) ? 01 : n * fac(n-1);

value main(list[value] args){
    for(i <- [1 .. 100000]){
       x= fac(20);
    }
    return 0;
}