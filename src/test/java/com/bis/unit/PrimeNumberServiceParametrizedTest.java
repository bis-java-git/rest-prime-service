package com.bis.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PrimeNumberServiceParametrizedTest extends AbstractPrimeServiceParameterizeTest {

    @Test
    public void testValidPrimeNumbers() {
        assertEquals(primeNumberService.getPrimeNumbers(upperLimit), expectedResult);
    }

    public PrimeNumberServiceParametrizedTest(final Integer upperLimit, final List<Integer> expectedResult) {
        super(upperLimit, expectedResult);
    }
}
