/**
 * Test unsupported @noimplement tag on a field in an annotation in the default package
 */
public @interface test6 {

    /**
	 * @noimplement
	 */
    public Object f1 = null;
}
