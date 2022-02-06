package bruzsal.betfair.util;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class Bench {

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    static final int COUNT = 10;
    static final int[] arr = new Random().ints(10_000_000, 0, 1_000_000).toArray();
    static final List<Integer> list = Arrays.stream(arr).boxed().toList();

    static final int MAX= 10_000_000;

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(value = COUNT, warmups = 1)
    public int getNumberOfDigits() {
        int count = 0;
        for (int i = 1; i <= MAX; i++)
            for (int j = i; j > 0; j /= 10)
                count++;
        return count;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(value = COUNT, warmups = 1)
    public int getNumberOfDigits3() {
        return IntStream.rangeClosed(1, MAX)
                .mapToObj(String::valueOf)
                .mapToInt(String::length)
                .sum();
    }

//    @Benchmark
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.SampleTime)
//    @Fork(value = COUNT, warmups = 0)
//    public void boxed(Blackhole blackhole) {
//        blackhole.consume(Arrays.stream(arr).boxed().dropWhile(integer -> integer < 100).count());
//    }

//
//    @Benchmark
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.SingleShotTime)
//    @Fork(value = COUNT, warmups = 1)
//    public void array(Blackhole blackhole) {
//        blackhole.consume(Arrays.stream(arr).dropWhile(integer -> integer > Integer.MAX_VALUE-100).count());
//    }
//
//    @Benchmark
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.SingleShotTime)
//    @Fork(value = COUNT, warmups = 1)
//    public void boxed(Blackhole blackhole) {
//        blackhole.consume(Arrays.stream(arr).boxed().dropWhile(integer -> integer > Integer.MAX_VALUE-100).count());
//    }

//    @Benchmark
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.SingleShotTime)
//    @Fork(value = COUNT, warmups = 3)
//    public long findPairsY() {
//
//        final int[] temp = {0, 0};//number,count
//        return Arrays.stream(arr).sorted()
//                .reduce(0, (a, b) -> {
//                    temp[1] = temp[0] == b ? temp[1] ^ 1 : 1;
//                    temp[0] = b;
//                    return a + 1 - temp[1];
//                });
//    }
//
//    @Benchmark
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.SingleShotTime)
//    @Fork(value = COUNT, warmups = 3)
//    public long findSortFirst() {
//        Arrays.sort(arr);
//        final int[] temp = {0, 0};//number,count
//        return Arrays.stream(arr)
//                .reduce(0, (a, b) -> {
//                    temp[1] = temp[0] == b ? temp[1] ^ 1 : 1;
//                    temp[0] = b;
//                    return a + 1 - temp[1];
//                });
//    }

}
