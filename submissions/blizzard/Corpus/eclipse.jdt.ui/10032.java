//12, 20 -> 12, 28   AllowLoadtime == true
package p;

class R {

    static int rG() {
        return 2;
    }

    static class S extends R {

        private static final int CONSTANT = p.R.rG();

        void f() {
            int d = CONSTANT;
        }
    }
}
