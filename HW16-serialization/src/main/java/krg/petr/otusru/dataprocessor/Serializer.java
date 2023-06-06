package krg.petr.otusru.dataprocessor;

import java.util.Map;

public interface Serializer {

    void serialize(Map<String, Double> data);
}
