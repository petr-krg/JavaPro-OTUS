package krg.petr.otusru;

import krg.petr.otusru.annotations.Log;
import krg.petr.otusru.interfaces.ITestLogging;

public class TestLogging implements ITestLogging {
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
