package krg.petr.otusru;

import krg.petr.otusru.utills.SharedResource;

import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        var executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> sharedResource.printSequence(true));
        executor.submit(() -> sharedResource.printSequence(false));

        executor.shutdown();
    }
}
