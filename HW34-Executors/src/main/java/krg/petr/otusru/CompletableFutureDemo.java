package krg.petr.otusru;

import krg.petr.otusru.utills.SharedResource;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        CompletableFuture<Void> futureFirst = CompletableFuture.runAsync(() -> sharedResource.printSequence(true));
        CompletableFuture<Void> futureSecond = CompletableFuture.runAsync(() -> sharedResource.printSequence(false));

        CompletableFuture<Void> allFuture = CompletableFuture.allOf(futureFirst, futureSecond);
        allFuture.join();
    }
}
