//9, 28, 9, 36
package p;

class A {

    enum Enum implements  {

        ONE() {
        }
        , TWO() {
        }
        ;
    }

    public void run() {
        System.out.println(Enum.ONE.name());
    }
}
