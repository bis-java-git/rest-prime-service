package com.bis.service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

public class PrimeNumberServiceImpl implements PrimeNumberService {

    public List<Integer> getPrimeNumbers(final Integer upperLimit) {
        List<Integer> primeList = new LinkedList<>();
        for (int i = 2; i <= upperLimit; i++) {
            if (isPrime(i)) {
                primeList.add(i);
            }
        }
        return primeList;
    }

    public List<Integer> getPrimeNumbersWithStream(final Integer upperLimit) {
        List<Integer> primeList = new LinkedList<>();
        rangeClosed(2, upperLimit).filter(this::isPrime).forEach(primeList::add);
        return primeList;
    }

    public List<Integer> getPrimeNumbersWithParallelStream(final Integer upperLimit) {
        List<Integer> collect = rangeClosed(2, upperLimit).parallel().filter(this::isPrime).boxed().collect(toList());
        return collect.stream().sorted().collect(toList());
    }

    public List<Integer> getPrimeNumberUsingForkAndPoolWithStream(final Integer upperLimit) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        List<Integer> primeList = forkJoinPool.submit(() ->
                rangeClosed(2, upperLimit).parallel().filter(this::isPrime).boxed().collect(toList())).get();
        forkJoinPool.shutdown();
        return primeList.stream().sorted().collect(toList());
    }

    @Override
    public List<Integer> getPrimeNumberUsingForkAndPool(final Integer upperLimit) throws ExecutionException, InterruptedException {
        return ForkJoinPrimes.doWork(2, upperLimit);
    }

    public List<Integer> getPrimeNumberUsingForkAndPoolWithStream2(final Integer upperLimit) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        List<Integer> primeList =CompletableFuture.supplyAsync(() ->
                rangeClosed(2, upperLimit).parallel().filter(this::isPrime).boxed().collect(toList()), forkJoinPool).get();
        forkJoinPool.shutdown();
        return primeList.stream().sorted().collect(toList());
    }

}
