package krg.petr.otusru.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import krg.petr.otusru.domain.Message;

public interface DataStore {

    Mono<Message> saveMessage(Message message);

    Flux<Message> loadMessages(String roomId);
}
