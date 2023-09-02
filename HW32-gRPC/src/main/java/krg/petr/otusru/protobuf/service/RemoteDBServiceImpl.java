package krg.petr.otusru.protobuf.service;

import io.grpc.stub.StreamObserver;
import krg.petr.otusru.protobuf.generated.NumberRequest;
import krg.petr.otusru.protobuf.generated.NumberResponse;
import krg.petr.otusru.protobuf.generated.RemoteDBServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

public class RemoteDBServiceImpl extends RemoteDBServiceGrpc.RemoteDBServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(RemoteDBServiceImpl.class);

    @Override
    public void generateNumber(NumberRequest numberRequest, StreamObserver<NumberResponse> numberResponse) {
        log.info(">>> сервер запущен ================================================================================");
        var firstValue = new AtomicLong(numberRequest.getFirstValue());

        for (int i = 0; i <= numberRequest.getLastValue(); i++) {
            var value = firstValue.incrementAndGet();
            var responseNumber = NumberResponse.newBuilder().setNumber(value).build();
            numberResponse.onNext(responseNumber);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numberResponse.onCompleted();
        log.info(">>> сервер остановлен =============================================================================");
    }
}
