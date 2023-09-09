package krg.petr.otusru.protobuf;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import krg.petr.otusru.protobuf.generated.NumberRequest;
import krg.petr.otusru.protobuf.generated.NumberResponse;
import krg.petr.otusru.protobuf.generated.RemoteDBServiceGrpc;
import krg.petr.otusru.protobuf.global.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class GRPCClient {
    private static final Logger log = LoggerFactory.getLogger(GRPCClient.class);

    private static final int MAX_COUNT = 50;

    public static void main(String[] args) throws InterruptedException {

        log.info(">>> клиент запущен ================================================================================");

        var chanel = ManagedChannelBuilder.forAddress(Constants.HOST, Constants.PORT)
                .usePlaintext()
                .build();

        var latch = new CountDownLatch(1);
        var serverValue = new AtomicLong();
        var newStub = RemoteDBServiceGrpc.newStub(chanel);
        var defaultInstance = NumberRequest.newBuilder()
                .setFirstValue(0)
                .setLastValue(30)
                .build();

        newStub.generateNumber(defaultInstance, new StreamObserver<NumberResponse>() {
            @Override
            public void onNext(NumberResponse value) {
                serverValue.set(value.getNumber());
                log.info(">>> данные сервера: {}", serverValue.get());
//                latch.countDown();
            }

            @Override
            public void onError(Throwable t) {
                log.info(">>> ERROR: {}", t.toString());
            }

            @Override
            public void onCompleted() {
                log.info(">>> Серверк окончил передачю ==============================================================");
                latch.countDown();
            }
        });

        var clientValue = 0;

        for (int i = 0; i < MAX_COUNT; i++) {
            Thread.sleep(1000);
            var value = serverValue.getAndSet(0);
            clientValue += value + 1;
            log.info(">>> данные клиента: {}", clientValue);
        }

        latch.await();
        chanel.shutdown();
    }
}
