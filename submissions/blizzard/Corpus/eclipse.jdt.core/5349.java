package test0226;

import java.util.*;

public class Test {

    public void foo(String s) {
        label: for (int i = 0; i < 10; i++) {
            break label;
        }
    }
}
