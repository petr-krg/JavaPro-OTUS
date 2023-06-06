package krg.petr.otusru.dataprocessor;

import krg.petr.otusru.model.Measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value
        Map<String, Double> aggregatedData = new HashMap<>();

        for (Measurement measurement : data) {
            String name = measurement.getName();
            double value = measurement.getValue();
            aggregatedData.merge(name, value, Double::sum);
        }
        return aggregatedData;
    }
}
