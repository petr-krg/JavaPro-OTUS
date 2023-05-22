package krg.petr.otusru.logingframework.interfaces;

public interface TestLogging {
    public void calcWithoutLog();
    void calculate();
    void calculate(int param);
    void calculate(int param1, int param2);
    void calculate(int param1, int param2, String param3);
}
