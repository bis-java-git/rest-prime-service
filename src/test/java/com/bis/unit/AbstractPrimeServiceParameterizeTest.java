package com.bis.unit;

import com.bis.service.PrimeNumberService;
import com.bis.service.PrimeNumberServiceImpl;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AbstractPrimeServiceParameterizeTest {

    protected final PrimeNumberService primeNumberService = new PrimeNumberServiceImpl();

    protected Integer upperLimit;

    protected List<Integer> expectedResult;

    public AbstractPrimeServiceParameterizeTest(final Integer upperLimit, final List<Integer> expectedResult) {
        this.expectedResult = expectedResult;
        this.upperLimit = upperLimit;

    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {0, Collections.EMPTY_LIST},
                {1, Collections.EMPTY_LIST},
                {2, Collections.singletonList(2)},
                {3, Arrays.asList(2, 3)},
                {4, Arrays.asList(2, 3)},
                {5, Arrays.asList(2, 3, 5)},
                {6, Arrays.asList(2, 3, 5)},
                {7, Arrays.asList(2, 3, 5, 7)},
                {8, Arrays.asList(2, 3, 5, 7)},
                {9, Arrays.asList(2, 3, 5, 7)},
                {10, Arrays.asList(2, 3, 5, 7)},
                {11, Arrays.asList(2, 3, 5, 7, 11)},
                {12, Arrays.asList(2, 3, 5, 7, 11)},
                {13, Arrays.asList(2, 3, 5, 7, 11, 13)},
                {17, Arrays.asList(2, 3, 5, 7, 11, 13, 17)},
                {19, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19)},
                {22, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19)},
                {23, Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23)}
        });
    }
}
