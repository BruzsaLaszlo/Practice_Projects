package test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Boolean.parseBoolean;
import static java.util.Comparator.comparing;

@State(Scope.Benchmark)
public class Comparator {

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    record T(int s, int i) implements Comparable<T> {
        @Override
        public int compareTo(T o) {
            int k = Integer.compare(s, o.s);
            return k == 0 ? Integer.compare(i, o.i) : k;
        }
    }

    static Random random = new Random();
    static final int MAX = 1_000_000;
    static List<T> list;
    static final int COUNT = 10;


    static {
        boolean result = false;
        try {
            System.out.println(parseBoolean(System.getProperty("compilerBlackholesEnabled")));
        } catch (IllegalArgumentException | NullPointerException e) {
        }
        assert Integer.MIN_VALUE - 1 == Integer.MAX_VALUE;
        System.out.println();
        list = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            list.add(new T(
                    random.nextInt(),
                    random.nextInt()));
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(value = COUNT, warmups = 1)
    public void sortTestOwnComparator(Blackhole blackhole) {
        var own = list.stream()
                .sorted()
                .toList();
        blackhole.consume(own);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(value = COUNT, warmups = 1)
    public void sortArraysort(Blackhole blackhole) {
        var l = new ArrayList<T>();
        Collections.copy(l, list);
        l.sort(T::compareTo);
        blackhole.consume(l);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(value = COUNT, warmups = 1)
    public void sortTest(Blackhole blackhole) {
        var expected = list.stream()
                .sorted(comparing(T::s).thenComparing(T::i).reversed())
                .toList();
        blackhole.consume(expected);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(value = COUNT, warmups = 1)
    public void esortTest2(Blackhole blackhole) {
        var actual = list.stream()
                .sorted(comparing(T::i))
                .sorted(comparing(T::s).reversed())
                .toList();
        blackhole.consume(actual);
    }

}
