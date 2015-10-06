package test;

/**
 * Created by jonathan on 6-10-15.
 */
public class TestObject2 extends TestObject {

    private final TestObject testObject;

    public TestObject2(String name, TestObject testObject) {
        super(name);
        this.testObject = testObject;
    }

    public TestObject getTestObject() {
        return testObject;
    }
}
