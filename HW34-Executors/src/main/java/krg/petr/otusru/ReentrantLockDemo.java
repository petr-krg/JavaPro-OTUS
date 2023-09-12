package krg.petr.otusru;

import krg.petr.otusru.utills.ReentrantLockResources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLockResources sharedResources = new ReentrantLockResources();

        /* // С использованием Executor
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> sharedResources.printSequence(true));
        executor.submit(() -> sharedResources.printSequence(false));
        executor.shutdown();
         */

        Thread thread1 = new Thread(() -> sharedResources.printSequence(true));
        Thread thread2 = new Thread(() -> sharedResources.printSequence(false));

        thread1.start();
        thread2.start();
    }
}
