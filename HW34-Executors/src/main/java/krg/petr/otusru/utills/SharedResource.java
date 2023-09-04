package krg.petr.otusru.utills;

public class SharedResource {
    private boolean threadFirstTurn = true;

    public synchronized void printSequence(boolean threadFirst) {
        for (int i = 0; i < 2; i++) {
            int start = (i == 0) ? 1 : 10;
            int end = (i == 0) ? 10 : 1;
            int step = (i == 0) ? 1 : -1;

            for (int j = start; ; j += step) {
                while (threadFirst != threadFirstTurn) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Поток " + (threadFirst ? "1: " : "2: ") + j);
                threadFirstTurn = !threadFirstTurn;
                notify();

                if (j == end) break;
            }
        }
    }
}