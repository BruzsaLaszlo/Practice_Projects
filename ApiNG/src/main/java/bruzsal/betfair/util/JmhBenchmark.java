package bruzsal.betfair.util;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@State(Scope.Benchmark)
public class JmhBenchmark {

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

//    @Benchmark
//    @BenchmarkMode(Mode.SingleShotTime)
//    @Fork(value = 1, warmups = 1)
//    public void init() {
//
//    }
//
//    @Benchmark
//    @OutputTimeUnit(TimeUnit.NANOSECONDS)
//    @BenchmarkMode(Mode.AverageTime)
//    @Fork(value = 1, warmups = 1)
//    public void doNothing() {
//    }

//    @Benchmark
//    @OutputTimeUnit(TimeUnit.NANOSECONDS)
//    @BenchmarkMode(Mode.SingleShotTime)
//    @Fork(value = COUNT, warmups = 3)
//    public void blackHole(Blackhole blackhole) {
//        blackhole.consume(new Object());
//    }

    static final int[] arr = new Random()
            .ints(100000,0,100)
            .toArray();
    static final List<Integer> list = Arrays.stream(arr).boxed().toList();
    static final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    static final int COUNT = 10;

//
//    @Benchmark
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.SingleShotTime)
//    @Fork(value = COUNT, warmups = 1)
//    public int findPairsStreamList() {
//        return Arrays.stream(arr)
//                .boxed()
//                .collect(Collectors.toMap(n -> n, n -> 1, Integer::sum))
//                .values().stream()
//                .mapToInt(v -> v / 2)
//                .sum();
//    }
//
//    @Benchmark
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.SingleShotTime)
//    @Fork(value = COUNT, warmups = 1)
//    public int findPairsStreamArray() {
//        return list.stream()
//                .collect(Collectors.toMap(n -> n, n -> 1, Integer::sum))
//                .values().stream()
//                .mapToInt(v -> v / 2)
//                .sum();
//    }
//
////    @Benchmark
////    @OutputTimeUnit(TimeUnit.MILLISECONDS)
////    @BenchmarkMode(Mode.SingleShotTime)
////    @Fork(value = COUNT, warmups = 1)
////    public void findPairsRecurcion() {
////        Arrays.sort(arr);
////        findPairsRecurcion(arr);
////    }
////
////    static int i = 0;
////
////    public int findPairsRecurcion(int[] arr) {
////        if (arr.length > 1) {
////            return switch (arr[0] - arr[1]) {
////                case 0 -> 1 + findPairsRecurcion(Arrays.copyOfRange(arr, 2, arr.length));
////                default -> findPairsRecurcion(Arrays.copyOfRange(arr, 1, arr.length));
////            };
////        }
////        return 0;
////    }
//
////    @Benchmark
////    @OutputTimeUnit(TimeUnit.MILLISECONDS)
////    @BenchmarkMode(Mode.SingleShotTime)
////    @Fork(value = COUNT, warmups = 1)
////    public int findPairs() {
////        return Arrays.stream(arr)
////                .distinct()
////                .mapToLong(o -> Arrays.stream(arr)
////                        .filter(c -> c == o)
////                        .count())
////                .mapToInt(l -> (int) l / 2)
////                .sum();
////    }
//
//    @Benchmark
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.SingleShotTime)
//    @Fork(value = COUNT, warmups = 1)
//    public int countCharsWithCompute() {
//        Map<Integer, Integer> result = new TreeMap<>();
//        for (int i : arr) {
//            result.compute(i, (k, v) -> v == null ? 1 : v + 1);
//        }
//        return result.values().stream().mapToInt(value -> value / 2).sum();
//    }
//
//    @Benchmark
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.SingleShotTime)
//    @Fork(value = COUNT, warmups = 1)
//    public long findPairsSajatStream() {
//        return Arrays.stream(arr)
//                .boxed()
//                .collect(groupingBy(
//                        o -> o,
//                        collectingAndThen(
//                                counting(),
//                                count -> count / 2
//                        )))
//                .values().stream()
//                .mapToLong(pairs -> pairs)
//                .sum();
//    }
//
//    @Benchmark
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.SingleShotTime)
//    @Fork(value = COUNT, warmups = 1)
//    public long findPairsSajatStreamList() {
//        return list.stream()
//                .collect(groupingBy(
//                        o -> o,
//                        collectingAndThen(
//                                counting(),
//                                count -> count / 2
//                        )))
//                .values().stream()
//                .mapToLong(pairs -> pairs)
//                .sum();
//    }
//
//    @Benchmark
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.SingleShotTime)
//    @Fork(value = COUNT, warmups = 1)
//    public int findPairsSajatArray() {
//        int[] counts = new int[100];
//        for (int i : arr) {
//            counts[i]++;
//        }
//
//        int result = 0;
//        for (int i = 0; i < counts.length; i++) {
//            result += counts[i] / 2;
//        }
//        return result;
//    }

}
