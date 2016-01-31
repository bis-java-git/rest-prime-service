package com.bis.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public interface PrimeNumberService {

    List<Integer> getPrimeNumbers(final Integer upperLimit);

    List<Integer> getPrimeNumbersWithStream(final Integer upperLimit);

    List<Integer> getPrimeNumbersWithParallelStream(final Integer upperLimit);

    List<Integer> getPrimeNumberUsingForkAndPoolWithStream(final Integer upperLimit) throws ExecutionException, InterruptedException;

    List<Integer> getPrimeNumberUsingForkAndPool(final Integer upperLimit) throws ExecutionException, InterruptedException;

    public List<Integer> getPrimeNumberUsingForkAndPoolWithStream2(final Integer upperLimit) throws ExecutionException, InterruptedException ;

    default Boolean isPrime(final Integer number){
        if ( number > 2 && number%2 == 0 ) {
            return false;
        }
        int topNumber = (int)Math.sqrt(number) + 1;
        for(int i = 3; i < topNumber; i+=2){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
}
