package krg.petr.otusru.lib;


import krg.petr.otusru.api.model.SensorData;

import java.util.List;

public interface SensorDataBufferedWriter {
    void writeBufferedData(List<SensorData> bufferedData);
}
