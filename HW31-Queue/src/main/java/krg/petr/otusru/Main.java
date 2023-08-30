package krg.petr.otusru;

import krg.petr.otusru.lib.SensorDataBufferedWriterFake;
import krg.petr.otusru.services.FakeSensorDataGenerator;
import krg.petr.otusru.services.SensorDataProcessingFlowImpl;
import krg.petr.otusru.services.SensorsDataQueueChannel;
import krg.petr.otusru.services.SensorsDataServerImpl;
import krg.petr.otusru.services.processors.SensorDataProcessorBuffered;
import krg.petr.otusru.services.processors.SensorDataProcessorCommon;
import krg.petr.otusru.services.processors.SensorDataProcessorErrors;
import krg.petr.otusru.services.processors.SensorDataProcessorRoom;

import java.util.concurrent.TimeUnit;

public class Main {
    private static final String ALL_ROOMS_BINDING = "*";
    private static final String ROOM_NAME_BINDING = "Комната: 4";
    private static final int BUFFER_SIZE = 15;
    private static final int SENSORS_COUNT = 4;

    private static final int SENSORS_DATA_QUEUE_CAPACITY = 1000;

    public static void main(String[] args) throws InterruptedException {

        // канал для передачи данных
        var sensorsDataChannel = new SensorsDataQueueChannel(SENSORS_DATA_QUEUE_CAPACITY);

        // получатель данных
        var sensorsDataServer = new SensorsDataServerImpl(sensorsDataChannel);

        // генератор данных
        var fakeSensorDataGenerator = new FakeSensorDataGenerator(sensorsDataServer, SENSORS_COUNT);

        // "насос" данных
        var sensorDataProcessingFlow = new SensorDataProcessingFlowImpl(sensorsDataChannel);

        // подписка на данные
        sensorDataProcessingFlow.bindProcessor(ALL_ROOMS_BINDING, new SensorDataProcessorCommon());
        sensorDataProcessingFlow.bindProcessor(ALL_ROOMS_BINDING, new SensorDataProcessorErrors());
        sensorDataProcessingFlow.bindProcessor(ROOM_NAME_BINDING, new SensorDataProcessorRoom(ROOM_NAME_BINDING));
        sensorDataProcessingFlow.bindProcessor(ALL_ROOMS_BINDING, new SensorDataProcessorBuffered(BUFFER_SIZE,
                new SensorDataBufferedWriterFake()));

        fakeSensorDataGenerator.start();
        sensorDataProcessingFlow.startProcessing();

        TimeUnit.SECONDS.sleep(10);

        fakeSensorDataGenerator.stop();
        sensorDataProcessingFlow.stopProcessing();
    }
}
