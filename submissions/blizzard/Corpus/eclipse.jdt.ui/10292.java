// 7, 19 -> 7, 28
class BodyDeclOnSameLine {

    private static final String BAR = "c";

    //$NON-NLS-1$ //$NON-NLS-2$
    private static final String FOO = "a";

    /* ambiguous */
    //$NON-NLS-1$ //$NON-NLS-2$
    String strange = "b";

    void m() {
        String s = FOO + BAR;
    }
}
