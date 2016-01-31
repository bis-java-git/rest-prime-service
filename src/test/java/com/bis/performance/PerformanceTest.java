package com.bis.performance;

import com.bis.service.PrimeNumberService;
import com.bis.service.PrimeNumberServiceImpl;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class PerformanceTest {

    private final static Logger LOGGER = LoggerFactory.getLogger("com.bis.performance.PerformanceTest");

    private static final Integer MAX_ITEMS_FOR_PERFORMANCE_TEST = 1000000;

    private static final long EXPECTED_PRIMES = 78498L;

    private final PrimeNumberService primeNumberService = new PrimeNumberServiceImpl();

    private void functionalTest(final Integer upperLimit, final Function<Integer, List<Integer>> function, final String message) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Integer> primes = function.apply(upperLimit);
        stopWatch.stop();
        assertThat(primes).hasSize((int) EXPECTED_PRIMES);
        LOGGER.debug(message, (stopWatch.getTime()));
    }

    @Test(timeout = 1000)
    public void forkAndJoinPerformanceWithStreamTest() {

        Function<Integer, List<Integer>> function = (upperLimit) -> {
            try {
                return primeNumberService.getPrimeNumberUsingForkAndPoolWithStream(upperLimit);
            } catch (ExecutionException | InterruptedException ignored) {
                return Collections.emptyList();
            }

        };

        functionalTest(MAX_ITEMS_FOR_PERFORMANCE_TEST, function, "ForkAndJoin with stream Total time in {} ms ");
    }

    @Test(timeout = 2000)
    public void forkAndJoinPerformanceWithStream2Test() {

        Function<Integer, List<Integer>> function = (upperLimit) -> {
            try {
                return primeNumberService.getPrimeNumberUsingForkAndPoolWithStream2(upperLimit);
            } catch (ExecutionException | InterruptedException ignored) {
                return Collections.emptyList();
            }
        };

        functionalTest(MAX_ITEMS_FOR_PERFORMANCE_TEST, function, "ForkAndJoin with stream 2 Total time in {} ms ");
    }

    @Test(timeout = 1000)
    public void forkAndJoinPerformanceTest() throws InterruptedException, ExecutionException {

        Function<Integer, List<Integer>> function = (Integer upperLimit) -> {
            try {
                return primeNumberService.getPrimeNumberUsingForkAndPool(upperLimit);
            } catch (ExecutionException | InterruptedException ignored) {
                return Collections.emptyList();
            }

        };
        functionalTest(MAX_ITEMS_FOR_PERFORMANCE_TEST, function, "ForkAndJoin Total time in {} ms ");
    }

    @Test(timeout = 500)
    public void sequentialPerformanceTest() throws InterruptedException, ExecutionException {
        Function<Integer, List<Integer>> function = primeNumberService::getPrimeNumbersWithStream;
        functionalTest(MAX_ITEMS_FOR_PERFORMANCE_TEST, function, "Sequential Total time in {} ms ");
    }

    @Test(timeout = 500)
    public void parallelPerformanceTest() throws InterruptedException, ExecutionException {
        Function<Integer, List<Integer>> function = primeNumberService::getPrimeNumbersWithParallelStream;
        functionalTest(MAX_ITEMS_FOR_PERFORMANCE_TEST, function, "Parallel Total time in {} ms ");
    }

    @Test(timeout = 500)
    public void simplePerformanceTest() throws InterruptedException, ExecutionException {
        Function<Integer, List<Integer>> function = primeNumberService::getPrimeNumbers;
        functionalTest(MAX_ITEMS_FOR_PERFORMANCE_TEST, function, "Simple Total time in {} ms");
    }
}
