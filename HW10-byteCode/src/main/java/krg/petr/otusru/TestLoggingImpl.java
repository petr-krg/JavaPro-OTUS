package krg.petr.otusru;

import krg.petr.otusru.logingframework.annotations.Log;
import krg.petr.otusru.logingframework.interfaces.TestLogging;

public class TestLoggingImpl implements TestLogging {

    @Override
    @Log
    public void calculate() {
        System.out.println("log method without parameters");
    }
    @Override
    @Log
    public void calculate(int param) {
        System.out.println(param);
    }

    @Override
    @Log
    public void calculate(int param1, int param2) {
        System.out.println(param1 + param2);
    }

    @Override
    @Log
    public void calculate(int param1, int param2, String param3) {
        System.out.println(param1 + param2 + param3);
    }
}
