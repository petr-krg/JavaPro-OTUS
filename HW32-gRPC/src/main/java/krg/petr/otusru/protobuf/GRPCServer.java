package krg.petr.otusru.protobuf;

import io.grpc.ServerBuilder;
import krg.petr.otusru.protobuf.global.Constants;
import krg.petr.otusru.protobuf.service.RemoteDBServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GRPCServer {
    private static final Logger log = LoggerFactory.getLogger(GRPCServer.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        var remoteDBService = new RemoteDBServiceImpl();

        var server = ServerBuilder
                .forPort(Constants.PORT)
                .addService(remoteDBService).build();
        server.start();
        log.info(">>> сервер ожидает подключения клиентов ===========================================================");
        server.awaitTermination();
    }
}
