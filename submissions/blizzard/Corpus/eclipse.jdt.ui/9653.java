package p;

//can't rename A.m to k - defined in superclass
class B {

    static void k() {
    }
}

class A extends B {

    void m() {
    }
}
