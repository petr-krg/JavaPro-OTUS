package krg.petr.otusru;

import krg.petr.otusru.handler.ComplexProcessor;
import krg.petr.otusru.listener.ListenerPrinterConsole;
import krg.petr.otusru.model.Message;
import krg.petr.otusru.processor.ProcessorConcatFields;
import krg.petr.otusru.processor.homework.ExceptionThrowingProcessor;

import java.time.LocalDateTime;
import java.util.List;

public class HomeWork {
    public static void main(String[] args) {
        var processors = List.of(
                new ProcessorConcatFields(),
                new ExceptionThrowingProcessor(LocalDateTime::now)
        );

        var complexProcessor = new ComplexProcessor(processors, ex -> {});
        var listenerPrinter = new ListenerPrinterConsole();
        complexProcessor.addListener(listenerPrinter);

        var message = new Message.Builder(1L)
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .build();

        var result = complexProcessor.handle(message);
        System.out.println("result: " + result);

        complexProcessor.removeListener(listenerPrinter);
    }
}