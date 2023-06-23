package krg.petr.otusru.listener.homework;

import krg.petr.otusru.listener.Listener;
import krg.petr.otusru.model.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    private final Map<Long, Message> history = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        Message copiedMsg = msg.copyMessage(msg);
        history.put(copiedMsg.getId(), copiedMsg);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.ofNullable(history.get(id));
    }

}
