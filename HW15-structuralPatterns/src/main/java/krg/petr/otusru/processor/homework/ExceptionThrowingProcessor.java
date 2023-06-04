package krg.petr.otusru.processor.homework;

import krg.petr.otusru.model.Message;
import krg.petr.otusru.processor.Processor;

import java.time.LocalDateTime;

public class ExceptionThrowingProcessor implements Processor {
    private MessageMemento memento;

    @Override
    public Message process(Message message) {
        int currentSecond = getCurrentSecond();

        if (currentSecond % 2 == 0) {
            memento = new MessageMemento(message);
            throw new RuntimeException("Even second exception");
        }

        return message;
    }

    private int getCurrentSecond() {
        LocalDateTime now = LocalDateTime.now();
        return now.getSecond();
    }

    public void rollback() {
        if (memento != null) {
            memento.restore();
            memento = null;
        }
    }

    private static class MessageMemento {
        private final Message message;

        private MessageMemento(Message message) {
            this.message = message;
        }

        private void restore() {
            message.toBuilder()
                    .field1(message.getField1())
                    .field2(message.getField2())
                    .field3(message.getField3())
                    .field4(message.getField4())
                    .field5(message.getField5())
                    .field6(message.getField6())
                    .field7(message.getField7())
                    .field8(message.getField8())
                    .field9(message.getField9())
                    .field10(message.getField10())
                    .field11(message.getField11())
                    .field12(message.getField12())
                    .field13(message.getField13())
                    .build();
        }
    }
}
