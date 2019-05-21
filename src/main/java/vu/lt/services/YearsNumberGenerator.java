package vu.lt.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Specializes;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.concurrent.Future;

@ViewScoped
@Model
@Specializes
public class YearsNumberGenerator extends SimpleYearsGenerator implements Serializable, YearGenerator {

    @Inject
    private NumberGenerator numberGenerator;

    public YearsNumberGenerator() {

    }

    @Futureable
    public Future<Integer> generateYearsWritten(Integer baseYear) {
        try {
            Thread.sleep(5000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        return new AsyncResult<>(numberGenerator.generateNumber(baseYear));
    }



}
