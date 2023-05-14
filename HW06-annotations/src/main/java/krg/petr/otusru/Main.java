package krg.petr.otusru;

import krg.petr.otusru.testframework.TestExecuter;

public class Main {
    public static void main(String[] args) {
        TestExecuter exTest = new TestExecuter("HW06-annotations", Main.class.getPackageName(), "tests");
        exTest.testsExecute();
    }
}