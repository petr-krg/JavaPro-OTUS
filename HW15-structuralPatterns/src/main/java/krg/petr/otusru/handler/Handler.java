package krg.petr.otusru.handler;

import krg.petr.otusru.listener.Listener;
import krg.petr.otusru.model.Message;

public interface Handler {
    Message handle(Message msg);

    void addListener(Listener listener);
    void removeListener(Listener listener);
}
