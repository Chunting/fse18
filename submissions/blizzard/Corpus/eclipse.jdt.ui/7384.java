package p;

interface I1 {

    void m(/*target*/
    List<String> l);
}

interface I2 {

    void m(/*ripple*/
    List<String> l);
}

class I implements I1, I2 {

    void m(/*ripple*/
    List<String> l);
}

class A {

    void m(List<String> l);
}
