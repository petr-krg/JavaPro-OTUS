package krg.petr.otusru.dataprocessor;

import krg.petr.otusru.model.Measurement;

import java.util.List;
import java.util.Map;

public interface Processor {

    Map<String, Double> process(List<Measurement> data);
}
