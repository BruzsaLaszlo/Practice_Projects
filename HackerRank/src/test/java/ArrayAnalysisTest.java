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
//        List<Character> input = List.of('','','','','','','','','','','','','',)


        assertEquals(new ArrayAnalysis.Pair(1, 6), new ArrayAnalysis().identifySubarray(input));
        assertEquals(new ArrayAnalysis.Pair(0, 3), new ArrayAnalysis().identifySubarray(input2));
        assertEquals(new ArrayAnalysis.Pair(0, 1), new ArrayAnalysis().identifySubarray(input3));
        assertEquals(new ArrayAnalysis.Pair(0, 9), new ArrayAnalysis().identifySubarray(input4));
    }


    @Test
    void ten() {
        List<Character> input = List.of('1', 'a', '2', 'b', '3', 'c', '4', 'd', '5', 'e');

        assertEquals(new ArrayAnalysis.Pair(0, 9), new ArrayAnalysis().identifySubarray(input));
    }

    private List<List<Character>> getAllCombination() {
        final int n = (int) Math.pow(2, 10);
        final List<List<Character>> allCombination = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String binary = String.format("%10s", Integer.toBinaryString(i)).replaceAll("[ 0]", "a");
            ;
            allCombination.add(binary.chars().mapToObj(value -> (char) value).toList());
        }
        return allCombination;
    }

    @Test
    void testAll() {
        List<List<Character>> allCombination = getAllCombination();
        ArrayAnalysis arrayAnalysis = new ArrayAnalysis();
        for (List<Character> array : allCombination) {
            arrayAnalysis.identifySubarray(array);
        }
        IntSummaryStatistics stat = ArrayAnalysis.getListN().stream().mapToInt(value -> value).summaryStatistics();
        assertEquals(ArrayAnalysis.getMethodCall(), stat.getCount());
        final int n = (int) (Math.log(stat.getCount()) / Math.log(2));
        double nlog2n = n * (Math.log(n) / Math.log(2));

        System.out.println("all : " + ArrayAnalysis.getMethodCall());
        System.out.println("sum : " + stat.getSum());
        System.out.println("avg : " + stat.getAverage());
        System.out.print("\n");
        System.out.println("max : " + stat.getMax());
        System.out.println("above nlog2(n): " + ArrayAnalysis.getListN().stream().filter(integer -> nlog2n < integer).count());
    }
}