package enums_out;

public enum A_test1150 implements  {

    A() {
    }
    ;

    public void foo() {
        extracted();
    }

    protected void extracted() {
        /*[*/
        /*]*/
        foo();
    }
}
