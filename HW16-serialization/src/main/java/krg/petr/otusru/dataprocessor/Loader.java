package krg.petr.otusru.dataprocessor;

import krg.petr.otusru.model.Measurement;

import java.util.List;

public interface Loader {

    List<Measurement> load();
}
