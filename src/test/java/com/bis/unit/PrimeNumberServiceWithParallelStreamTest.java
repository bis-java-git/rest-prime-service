package com.bis.unit;

import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PrimeNumberServiceWithParallelStreamTest extends AbstractPrimeServiceTest {

    @Test
    public void testInvalidPrimeNumbersZero() {
        assertThat(primeNumberService.getPrimeNumbersWithParallelStream(0)).isEmpty();
    }

    @Test
    public void testInvalidPrimeNumbersOne() {
        assertThat(primeNumberService.getPrimeNumbersWithParallelStream(1)).isEmpty();
    }

    @Test
    public void testSingleValidPrimeNumber() {
        assertEquals(primeNumberService.getPrimeNumbersWithParallelStream(2), Collections.singletonList(2));
    }

    @Test
    public void testInclusiveLastPrimeNumber() {
        assertEquals(primeNumberService.getPrimeNumbersWithParallelStream(MAX_INCLUSIVE_PRIME_NUMBER), EXPECTED_INCLUSIVE_PRIME_LIST);
    }

    @Test
    public void testLargePrimeNumber() {
        assertThat(primeNumberService.getPrimeNumbersWithParallelStream(MAXIMUM_PRIME_NUMBER)).hasSize(EXPECTED_PRIME_NUMBERS_FOR_100000);
    }
}
