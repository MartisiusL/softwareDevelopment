package vu.lt.services;

import javax.ejb.AsyncResult;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.concurrent.Future;

@ViewScoped
@Model
public class SimpleYearsGenerator implements YearGenerator, Serializable {

    @Inject
    private NumberGenerator numberGenerator;

    public NumberGenerator getNumberGenerator() {
        return numberGenerator;
    }

    public void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public SimpleYearsGenerator() {

    }

    public Future<Integer> generateYearsWritten(Integer baseYear) {
        return new AsyncResult<>(numberGenerator.generateNumber(baseYear));
    }

}
