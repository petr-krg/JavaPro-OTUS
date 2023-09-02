package krg.petr.otusru.api;

import krg.petr.otusru.api.model.SensorData;

public interface SensorsDataServer {
    void onReceive(SensorData sensorData);
}
