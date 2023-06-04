package krg.petr.otusru.processor.homework;

import krg.petr.otusru.model.Message;
import krg.petr.otusru.processor.Processor;

public class SwapFieldProcessor implements Processor {
    @Override
    public Message process(Message message) {
        String field11 = message.getField11();
        String field12 = message.getField12();

        Message.Builder builder = message.toBuilder()
                .field11(field12)
                .field12(field11);

        return builder.build();
    }
}
