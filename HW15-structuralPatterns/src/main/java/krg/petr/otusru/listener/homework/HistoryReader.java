package krg.petr.otusru.listener.homework;

import krg.petr.otusru.model.Message;

import java.util.Optional;

public interface HistoryReader {

    Optional<Message> findMessageById(long id);
}
