package com.bis.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PrimeNumberServiceWithStreamParameterizedTest extends AbstractPrimeServiceParameterizedTest {

    public PrimeNumberServiceWithStreamParameterizedTest(Integer upperLimit, List<Integer> expectedResult) {
        super(upperLimit, expectedResult);
    }

    @Test
    public void testGetPrimeNumbersSequential() {
        assertEquals(primeNumberService.getPrimeNumbersWithStream(upperLimit), expectedResult);
    }
}
