package krg.petr.otusru.listener.homework;

import krg.petr.otusru.listener.Listener;
import krg.petr.otusru.model.Message;

import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    @Override
    public void onUpdated(Message msg) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        throw new UnsupportedOperationException();
    }
}
