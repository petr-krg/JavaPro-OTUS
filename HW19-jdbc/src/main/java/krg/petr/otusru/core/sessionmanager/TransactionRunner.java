package krg.petr.otusru.core.sessionmanager;

public interface TransactionRunner {

    <T> T doInTransaction(TransactionAction<T> action);
}
