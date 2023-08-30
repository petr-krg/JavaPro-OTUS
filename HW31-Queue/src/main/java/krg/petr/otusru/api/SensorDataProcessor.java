package krg.petr.otusru.api;


import krg.petr.otusru.api.model.SensorData;

public interface SensorDataProcessor {
    void process(SensorData data);

    default void onProcessingEnd() {
    }
}
