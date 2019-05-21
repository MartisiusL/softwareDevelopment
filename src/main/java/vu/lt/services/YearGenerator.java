package vu.lt.services;

import java.util.concurrent.Future;

public interface YearGenerator {

    Future<Integer> generateYearsWritten(Integer baseYear);
}
