package krg.petr.otusru.testframework;

public class Statistics {
    private int runCount = 0;
    private int errorCount = 0;

    public void testStarted() {
        runCount++;
    }

    public void testFailed() {
        errorCount++;
    }

    private Integer getSuccess(){

        return runCount - errorCount;
    }

    public String getSummary() {
        return String.format("Total tests running: %d\nSuccessfully passed tests - %d\nFallenTests - %d",
                runCount, getSuccess(), errorCount);
    }
}
