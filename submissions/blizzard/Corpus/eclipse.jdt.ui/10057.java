package p;

public class A {

    private static class SomeInner {

        private static String a;

        // should increase visibility of a, b, Inner, SomeInner
        private static class Inner {

            private String b = a;
        }
    }

    {
        new SomeInner.Inner().b = "";
    }
}
