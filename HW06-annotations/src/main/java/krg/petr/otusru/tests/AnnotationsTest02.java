package krg.petr.otusru.tests;


import krg.petr.otusru.testframework.annotations.After;
import krg.petr.otusru.testframework.annotations.Before;
import krg.petr.otusru.testframework.annotations.Test;

public class AnnotationsTest02 {

    @Before
    public void setUp01() {

        System.out.println("Setting up test #01 ...");
    }

    @Before
    public void setUp02() {

        System.out.println("Setting up test #02 ...");
    }

    @Test
    public void testMethod02() {

        System.out.println("Running testMethod02 ...");
    }

    @Test
    public void testMethod03() {
        System.out.println("Running testMethod03 ...");
        throw new NullPointerException();
    }

    @After
    public void tearDown() {

        System.out.println("Tearing down test ...");
    }

}
