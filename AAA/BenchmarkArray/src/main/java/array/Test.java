package array;

import hackerrank.ArrayAnalysis;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class Test {

    private static final int COUNT = 1;
    private static final int WARMUPS = 0;

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    private List<List<Character>> allCombination;

    public static final int N = 24;

    private List<List<Character>> getAllCombination() {
        final int n = (int) Math.pow(2, N);
        final List<List<Character>> allCombination = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String binary = String.format("%24s", Integer.toBinaryString(i)).replaceAll("[ 0]", "a");
            allCombination.add(binary.chars().mapToObj(value -> (char) value).toList());
        }
        return allCombination;
    }

    @Setup
    public void setUp() {
        allCombination = getAllCombination();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(value = COUNT, warmups = WARMUPS)
    public void o2n(Blackhole blackhole) {
        ArrayAnalysis arrayAnalysis = new ArrayAnalysis();
        for (List<Character> array : allCombination) {
            ArrayAnalysis.Pair pair = arrayAnalysis.identifySubarray(array);
            blackhole.consume(pair);
//            if (pair == null)
//                System.out.println(array);
//            else
//                System.out.println(array + "  [" + pair.a() + "," + pair.b() + "]");
        }
        IntSummaryStatistics stat = arrayAnalysis.getListN().stream().mapToInt(value -> value).summaryStatistics();
        assert arrayAnalysis.getMethodCall() == stat.getCount();
        final int n = (int) (Math.log(stat.getCount()) / Math.log(2));
        double nlog2n = n * (Math.log(n) / Math.log(2));

        System.out.println("all : " + arrayAnalysis.getMethodCall());
        System.out.println("sum : " + stat.getSum());
        System.out.println("avg : " + stat.getAverage());
        System.out.print("\n");
        System.out.println("max : " + stat.getMax());
        System.out.println("above O(2n-1): " + arrayAnalysis.getListN().stream().filter(integer -> 2 * n - 1 < integer).count());
        System.out.println("above nlog2(n): " + arrayAnalysis.getListN().stream().filter(integer -> nlog2n < integer).count());
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(value = COUNT, warmups = WARMUPS)
    public void on2() {
        ArrayAnalysis arrayAnalysis = new ArrayAnalysis();
        for (List<Character> array : allCombination) {
            ArrayAnalysis.Pair pair = arrayAnalysis.identifySubarrayOn2(array);
//            if (pair == null)
//                System.out.println(array);
//            else
//                System.out.println(array + "  [" + pair.a() + "," + pair.b() + "]");
        }
        IntSummaryStatistics stat = arrayAnalysis.getListN().stream().mapToInt(value -> value).summaryStatistics();
        assert arrayAnalysis.getMethodCall() == stat.getCount();
        final int n = (int) (Math.log(stat.getCount()) / Math.log(2));
        double nlog2n = n * (Math.log(n) / Math.log(2));

        System.out.println("all : " + arrayAnalysis.getMethodCall());
        System.out.println("sum : " + stat.getSum());
        System.out.println("avg : " + stat.getAverage());
        System.out.print("\n");
        System.out.println("max : " + stat.getMax());
        System.out.println("above O(2n-1): " + arrayAnalysis.getListN().stream().filter(integer -> 2 * n - 1 < integer).count());
        System.out.println("above nlog2(n): " + arrayAnalysis.getListN().stream().filter(integer -> nlog2n < integer).count());
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.SingleShotTime)
    @Fork(value = COUNT, warmups = WARMUPS)
    public void on22() {
        ArrayAnalysis arrayAnalysis = new ArrayAnalysis();
        for (int b = 0; b < Math.pow(2, N); b++) {
            ArrayAnalysis.Pair pair = arrayAnalysis.identifySubarrayOn22(b, N);
//            if (pair == null)
//                System.out.println(array);
//            else
//                System.out.println(array + "  [" + pair.a() + "," + pair.b() + "]");
        }
        IntSummaryStatistics stat = arrayAnalysis.getListN().stream().mapToInt(value -> value).summaryStatistics();
        assert arrayAnalysis.getMethodCall() == stat.getCount();
        final int n = (int) (Math.log(stat.getCount()) / Math.log(2));
        double nlog2n = n * (Math.log(n) / Math.log(2));

        System.out.println("all : " + arrayAnalysis.getMethodCall());
        System.out.println("sum : " + stat.getSum());
        System.out.println("avg : " + stat.getAverage());
        System.out.print("\n");
        System.out.println("max : " + stat.getMax());
        System.out.println("above O(2n-1): " + arrayAnalysis.getListN().stream().filter(integer -> 2 * n - 1 < integer).count());
        System.out.println("above nlog2(n): " + arrayAnalysis.getListN().stream().filter(integer -> nlog2n < integer).count());
    }

}
