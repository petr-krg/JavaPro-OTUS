package krg.petr.otusru.utills;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockResources {
    private boolean threadFirstTurn = true;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void printSequence(boolean threadFirst) {
        for (int i = 0; i < 2; i++) {
            lock.lock();
            try {
                int start = (i == 0) ? 1 : 10;
                int end = (i == 0) ? 10 : 1;
                int step = (i == 0) ? 1 : -1;

                for (int j = start; ; j += step) {
                    while (threadFirst != threadFirstTurn) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("Поток " + (threadFirst ? "1: " : "2: ") + j);
                    threadFirstTurn = !threadFirstTurn;
                    condition.signalAll();

                    if (j == end) break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
