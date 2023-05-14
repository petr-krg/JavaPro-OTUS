package krg.petr.otusru.tests;

import krg.petr.otusru.testframework.annotations.After;
import krg.petr.otusru.testframework.annotations.Before;
import krg.petr.otusru.testframework.annotations.Test;

public class AnnotationsTest01 {
    @Before
    public void setUp() {

        System.out.println("Setting up test...");
    }

    @Test
    public void testMethod01() {
        System.out.println("Running testMethod...");
    }

    @After
    public void tearDown() {

        System.out.println("Tearing down test...");
    }
}
