package krg.petr.otusru.processor.homework;

import krg.petr.otusru.processor.Processor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionThrowingProcessorTest {

    @Test
    void EvenSecond() {
        DateTimeProvider dateTimeProvider = new DateTimeProvider() {
            @Override
            public LocalDateTime getNowDateTime() {
                return LocalDateTime.of(3, Month.JANUARY, 4, 1, 2, 6);
            }
        };

        Processor processor = new ExceptionThrowingProcessor(dateTimeProvider);
        assertThrows(RuntimeException.class, () -> processor.process(null), "Even second");
    }

    @Test
    void OddSecond() {
        DateTimeProvider dateTimeProvider = new DateTimeProvider() {
            @Override
            public LocalDateTime getNowDateTime() {
                return LocalDateTime.of(3, Month.JANUARY, 4, 1, 2, 7);
            }
        };

        Processor processor = new ExceptionThrowingProcessor(dateTimeProvider);
        assertDoesNotThrow(() -> processor.process(null), "Odd second");
    }
}
