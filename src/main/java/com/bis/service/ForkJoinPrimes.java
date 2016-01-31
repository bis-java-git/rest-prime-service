package com.bis.service;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import static java.util.stream.Collectors.toList;

public class ForkJoinPrimes extends RecursiveAction {

    private static final Integer MAX_ITEMS_FOR_PERFORMANCE_TEST = 1000000;

    private int maxWorkDivisor = 2048;

    private int workSize = MAX_ITEMS_FOR_PERFORMANCE_TEST/maxWorkDivisor;

    private int lowerLimit;

    private int upperLimit;

    private Queue<Set<Integer>> resultsQueue;

    private PrimeNumberService primeNumberService = new PrimeNumberServiceImpl();

    public ForkJoinPrimes(final int lowerLimit, final int upperLimit, final Queue<Set<Integer>> resultsQueue) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.resultsQueue = resultsQueue;
    }

    private Set<Integer> findPrimeNumbers(final int lowerLimit, final int upperLimit) {
        Set<Integer> primes = new LinkedHashSet<>();
        for (int i = lowerLimit; i <= upperLimit; i++) {
            if (primeNumberService.isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    protected void compute() {
        if (upperLimit - lowerLimit < workSize) {
            resultsQueue.offer(findPrimeNumbers(lowerLimit, upperLimit));
        } else {
            // split
            int mid = (lowerLimit + upperLimit) / 2;

            invokeAll(new ForkJoinPrimes(lowerLimit, mid, resultsQueue),
                    new ForkJoinPrimes(mid + 1, upperLimit, resultsQueue));
        }
    }

    public static List<Integer> doWork(final int lowerLimit, final int upperLimit) {
        final Queue<Set<Integer>> resultsQueue = new ConcurrentLinkedQueue<>();
        final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(new ForkJoinPrimes(lowerLimit, upperLimit, resultsQueue));

        final Set<Integer> primes = new LinkedHashSet<>();
        while (resultsQueue.size() > 0) {
            primes.addAll(resultsQueue.poll());
        }
        forkJoinPool.shutdown();
        return primes.stream().sorted().collect(toList());
    }
}
