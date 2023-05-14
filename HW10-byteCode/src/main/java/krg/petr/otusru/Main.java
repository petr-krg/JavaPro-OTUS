package krg.petr.otusru;

import krg.petr.otusru.interfaces.ITestLogging;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        TestLogging ts = new TestLogging();
        ts.calculate(2);
        ts.calculate(3,2);
        ts.calculate(5,6," - test01");

        ITestLogging logging = (ITestLogging) Proxy.newProxyInstance(
                ITestLogging.class.getClassLoader(),
                new Class<?>[]{ITestLogging.class},
                new Handler(new TestLogging())
        );

        logging.calculate(5);
        logging.calculate(6,4);
        logging.calculate(10,12," - test02");
    }
}