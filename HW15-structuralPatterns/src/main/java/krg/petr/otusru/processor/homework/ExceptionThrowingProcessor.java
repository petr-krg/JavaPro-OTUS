package krg.petr.otusru.processor.homework;

import krg.petr.otusru.model.Message;
import krg.petr.otusru.processor.Processor;

public class ExceptionThrowingProcessor implements Processor {

    private final DateTimeProvider dateTimeProvider;

    public ExceptionThrowingProcessor(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        if (dateTimeProvider.getNowDateTime().getSecond() % 2 == 0) {
            throw new RuntimeException("Even second exception");
        }

        return message;
    }
}
