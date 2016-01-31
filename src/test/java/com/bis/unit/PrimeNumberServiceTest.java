package com.bis.unit;

import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PrimeNumberServiceTest extends AbstractPrimeServiceTest {

    @Test
    public void testInvalidPrimeNumbersZero() {
        assertThat(primeNumberService.getPrimeNumbers(0)).isEmpty();
    }

    @Test
    public void testInvalidPrimeNumbersOne() {
        assertThat(primeNumberService.getPrimeNumbers(1)).isEmpty();
    }

    @Test
    public void testSingleValidPrimeNumber() {
        assertEquals(primeNumberService.getPrimeNumbers(2), Collections.singletonList(2));
    }

    @Test
    public void testInclusiveLastPrimeNumber() {
        assertEquals(primeNumberService.getPrimeNumbers(MAX_INCLUSIVE_PRIME_NUMBER), EXPECTED_INCLUSIVE_PRIME_LIST);
    }

    @Test
    public void testLargePrimeNumber() {
        assertThat(primeNumberService.getPrimeNumbers(MAXIMUM_PRIME_NUMBER)).hasSize(EXPECTED_PRIME_NUMBERS_FOR_100000);
    }
}
