package krg.petr.otusru.tests;


import krg.petr.otusru.testframework.annotations.After;
import krg.petr.otusru.testframework.annotations.Before;
import krg.petr.otusru.testframework.annotations.Test;

import java.util.Random;

public class AnnotationsTest02 {

    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(100);
    }

    @Before
    public void setUp01() {

        System.out.println("Setting up test #01 ...");
        if ((getRandomNumber() % 2) != 0) {
            throw new NullPointerException();
        }
    }

    @Test
    public void testMethod02() {

        System.out.println("Running testMethod02 ...");
    }

    @Test
    public void testMethod03() {
        System.out.println("Running testMethod03 ...");
        if ((getRandomNumber() % 2) != 0) {
            throw new NullPointerException();
        }
    }

    @After
    public void tearDown() {

        System.out.println("Tearing down test ...");
    }

}
