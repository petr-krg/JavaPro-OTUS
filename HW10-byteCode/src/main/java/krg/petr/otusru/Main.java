package krg.petr.otusru;


import krg.petr.otusru.logingframework.LoggingProxy;
import krg.petr.otusru.logingframework.interfaces.TestLogging;

public class Main {
    public static void main(String[] args) {
        TestLogging logging = (TestLogging) LoggingProxy.createLoggingProxy(TestLogging.class, new TestLoggingImpl());

        if (logging == null) {
            System.out.println("The program execution is not possible, the object 'LoggingProxy' was not received.");
            System.exit(1);
        }

        logging.calculate();
        logging.calculate(5);
        logging.calculate(6,4);
        logging.calculate(10,12," - test02");
    }
}