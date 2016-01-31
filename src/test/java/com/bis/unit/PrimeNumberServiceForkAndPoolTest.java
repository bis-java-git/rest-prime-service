package com.bis.unit;

import org.junit.Test;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PrimeNumberServiceForkAndPoolTest extends AbstractPrimeServiceTest {

    @Test
    public void testInvalidPrimeNumbersZero() throws ExecutionException, InterruptedException {
        assertThat(primeNumberService.getPrimeNumberUsingForkAndPool(0)).isEmpty();
    }

    @Test
    public void testInvalidPrimeNumbersOne() throws ExecutionException, InterruptedException {
        assertThat(primeNumberService.getPrimeNumberUsingForkAndPool(1)).isEmpty();
    }

    @Test
    public void testSingleValidPrimeNumber() throws ExecutionException, InterruptedException {
        assertEquals(primeNumberService.getPrimeNumberUsingForkAndPool(2), Collections.singletonList(2));
    }

    @Test
    public void testInclusiveLastPrimeNumber() throws ExecutionException, InterruptedException {
        assertEquals(primeNumberService.getPrimeNumberUsingForkAndPool(MAX_INCLUSIVE_PRIME_NUMBER), EXPECTED_INCLUSIVE_PRIME_LIST);
    }

    @Test
    public void testLargePrimeNumber() throws ExecutionException, InterruptedException {
        assertThat(primeNumberService.getPrimeNumberUsingForkAndPool(MAXIMUM_PRIME_NUMBER)).hasSize(EXPECTED_PRIME_NUMBERS_FOR_100000);
    }
}
