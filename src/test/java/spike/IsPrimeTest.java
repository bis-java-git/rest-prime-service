package spike;

import org.apache.commons.lang3.time.StopWatch;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

public class IsPrimeTest {

    Boolean isPrime(final Integer number) {
        if (number > 2 && number % 2 == 0) {
            return false;
        }
        int topNumber = (int) Math.sqrt(number) + 1;
        for (int i = 3; i < topNumber; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    Boolean isPrime2(final Integer number) {
        if (number < 2) {
            return false;
        }
        if (number > 2 && number % 2 == 0) {
            return false;
        }
        int topNumber = (int) Math.sqrt(number) + 1;
        return IntStream.iterate(3, i -> i + 2).limit(topNumber / 2 - 1).filter(n -> number % n == 0).findAny().isPresent() ? false : true;
    }

    public void doWork() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Integer> list = rangeClosed(2, 1_000_000).parallel().filter((number1) -> isPrime(number1)).boxed().collect(toList());
        stopWatch.stop();
        System.out.println("Time taken for isPrime1 " + stopWatch.getTime() + " ms");
        System.out.println("isPrime1 size " + list.size());

        stopWatch.reset();
        stopWatch.start();
        list = rangeClosed(2, 1_000_000).parallel().filter((number) -> isPrime2(number)).boxed().collect(toList());
        stopWatch.stop();
        System.out.println("Time taken for isPrime2 " + stopWatch.getTime() + " ms");
        System.out.println("isPrime2 size " + list.size());
    }

    public static void main(String... args) {
        new IsPrimeTest().doWork();
    }
}
