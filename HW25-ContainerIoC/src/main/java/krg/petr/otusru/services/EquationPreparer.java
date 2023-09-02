package krg.petr.otusru.services;

import krg.petr.otusru.model.Equation;

import java.util.List;

public interface EquationPreparer {
    List<Equation> prepareEquationsFor(int base);
}
