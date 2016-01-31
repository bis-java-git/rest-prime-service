package com.bis.unit;

import com.bis.service.PrimeNumberService;
import com.bis.service.PrimeNumberServiceImpl;

import java.util.Arrays;
import java.util.List;

public class AbstractPrimeServiceTest {

    protected final PrimeNumberService primeNumberService = new PrimeNumberServiceImpl();

    public final static Integer MAXIMUM_PRIME_NUMBER= 1_000_00;

    public final static int EXPECTED_PRIME_NUMBERS_FOR_100000=9592;

    protected List<Integer> EXPECTED_INCLUSIVE_PRIME_LIST = Arrays.asList(2, 3, 5, 7, 11, 13);

    public final static Integer MAX_INCLUSIVE_PRIME_NUMBER= 15;

}
