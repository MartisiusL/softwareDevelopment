package vu.lt.usecases;

import vu.lt.interceptors.LoggedInvocation;
import vu.lt.services.YearGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class GenerateBookYearNumber implements Serializable {
    @Inject
    YearGenerator yearsNumberGenerator;

    private Future<Integer> yearNumberGenerationTask = null;

    @LoggedInvocation
    public String generateNewYearNumber() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        yearNumberGenerationTask = yearsNumberGenerator.generateYearsWritten(1978);
        return  "/bookDetails.xhtml?faces-redirect=true&bookId=" + requestParameters.get("bookId");
    }

    public boolean isYearGenerationRunning() {
        return yearNumberGenerationTask != null && !yearNumberGenerationTask.isDone();
    }

    public String getYearGenerationStatus() throws ExecutionException, InterruptedException {
        if (yearNumberGenerationTask == null) {
            return null;
        } else if (isYearGenerationRunning()) {
            return "Year generation in progress";
        }
        return "Suggested year number: " + yearNumberGenerationTask.get();
    }
}