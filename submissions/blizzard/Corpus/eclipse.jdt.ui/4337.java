//renaming I.m to k
package p;

interface I {

    void k();
}

interface I2 {

    void k();
}

interface I3 extends I, I2 {
}

class A implements I3 {

    public void k() {
    }
}
