package com.bis.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PrimeNumberServiceForkAndPoolParameterizeTest extends AbstractPrimeServiceParameterizeTest {

    public PrimeNumberServiceForkAndPoolParameterizeTest(Integer upperLimit, List<Integer> expectedResult) {
        super(upperLimit, expectedResult);
    }

    @Test
    public void testGetPrimeNumbers() throws ExecutionException, InterruptedException {
        assertEquals(primeNumberService.getPrimeNumberUsingForkAndPool(upperLimit), expectedResult);
    }
}
