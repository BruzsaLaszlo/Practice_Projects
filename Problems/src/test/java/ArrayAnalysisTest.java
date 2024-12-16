import hackerrank.ArrayAnalysis;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayAnalysisTest {

    @Test
    void identifySubarray() {
        List<Character> input = List.of('0', 'a', 'c', '4', '1', '2', 'b', '0', '2', '3');
        List<Character> input2 = List.of('a', '0', 'c', '4', '1', '2', 'b', '0', '2', '3');
        List<Character> input3 = List.of('0', 'c', '4', '1', '2', 'b', '0', '2', '3', 'a');
        List<Character> input4 = List.of('d', 'c', '1', '2', '0', '2', '3', 'i', 'b', 'a');
        List<Character> input5 = List.of('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', '1');


        assertEquals(new ArrayAnalysis.Pair(1, 6), new ArrayAnalysis().identifySubarray(input));
        assertEquals(new ArrayAnalysis.Pair(0, 3), new ArrayAnalysis().identifySubarray(input2));
        assertEquals(new ArrayAnalysis.Pair(1, 2), new ArrayAnalysis().identifySubarray(input3));
        assertEquals(new ArrayAnalysis.Pair(0, 9), new ArrayAnalysis().identifySubarray(input4));
        assertEquals(new ArrayAnalysis.Pair(8, 9), new ArrayAnalysis().identifySubarray(input5));
    }


    @Test
    void ten() {
        List<Character> input = List.of('1', 'a', '2', 'b', '3', 'c', '4', 'd', '5', 'e');

        assertEquals(new ArrayAnalysis.Pair(0, 9), new ArrayAnalysis().identifySubarray(input));
    }

    private List<List<Character>> getAllCombination() {
        final int n = (int) Math.pow(2, 17);
        final List<List<Character>> allCombination = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String binary = String.format("%17s", Integer.toBinaryString(i)).replaceAll("[ 0]", "a");
            allCombination.add(binary.chars().mapToObj(value -> (char) value).toList());
        }
        return allCombination;
    }

    @Test
    void testNew() {
        List<Character> input5 = List.of('a', 'a', 'a', 'a', 'a', '1', 'a', 'a', 'a', '1');
        assertEquals(new ArrayAnalysis.Pair(5, 6), new ArrayAnalysis().identifySubarray(input5));
    }

    @Test
    void testAll() {
        List<List<Character>> allCombination = getAllCombination();
        ArrayAnalysis arrayAnalysis = new ArrayAnalysis();
        for (List<Character> array : allCombination) {
            ArrayAnalysis.Pair pair = arrayAnalysis.identifySubarray(array);
//            if (pair == null)
//                System.out.println(array);
//            else
//                System.out.println(array + "  [" + pair.a() + "," + pair.b() + "]");
        }
        IntSummaryStatistics stat = arrayAnalysis.getListN().stream().mapToInt(value -> value).summaryStatistics();
        assertEquals(arrayAnalysis.getMethodCall(), stat.getCount());
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

    @Test
    void testBinary() {
        ArrayAnalysis arrayAnalysis = new ArrayAnalysis();
        final int bitcount = 10;
        for (int b = 0; b < Math.pow(2, bitcount); b++) {
            ArrayAnalysis.Pair pair = arrayAnalysis.identifySubarrayOn22(b, bitcount);
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

    @Test
    void name() {
        ArrayAnalysis arrayAnalysis = new ArrayAnalysis();

        assertEquals(new ArrayAnalysis.Pair(1, 6), arrayAnalysis.identifySubarrayOn22(Integer.parseInt("0110001000", 2), 10));
        assertEquals(new ArrayAnalysis.Pair(0, 3), arrayAnalysis.identifySubarrayOn22(Integer.parseInt("1010001000", 2), 10));
        assertEquals(new ArrayAnalysis.Pair(1, 2), arrayAnalysis.identifySubarrayOn22(Integer.parseInt("0100010001", 2), 10));
        assertEquals(new ArrayAnalysis.Pair(0, 9), arrayAnalysis.identifySubarrayOn22(Integer.parseInt("0110001011", 2), 10));
        assertEquals(new ArrayAnalysis.Pair(8, 9), arrayAnalysis.identifySubarrayOn22(Integer.parseInt("1111111110", 2), 10));
    }
}