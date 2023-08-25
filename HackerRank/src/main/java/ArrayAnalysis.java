import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ArrayAnalysis {

    public record Pair(int a, int b) {
    }

    @Getter
    private static int methodCall;

    @Getter
    private static List<Integer> listN = new ArrayList<>();

    private static int n;

    public Pair identifySubarray(List<Character> array) {
        methodCall++;
        n = 1;

        int count = getCount(0, array.size(), array);
        if (count == 0 || count == array.size() || array.size() < 2) {
            listN.add(1);
            return null;
        }
        int max = Math.min(count, array.size() - count) * 2;
        if (max == array.size()) {
            listN.add(1);
            return new Pair(0, max - 1);
        }

        Pair find = keres(max, array);
        listN.add(n);
        return find;
    }

    private Pair keres(int max, List<Character> array) {
        for (int i = 0; i + max < array.size(); i++) {
            n++;
            int count = getCount(i, i + max, array);
            if (count * 2 == max) {
                return new Pair(i, i + max - 1);
            }
        }
        return keres(max - 1, array);
    }

    private int getCount(int min, int max, List<Character> array) {
        int count = 0;
        for (int i = min; i < max; i++) {
            if (Character.isDigit(array.get(i))) count++;
        }
        return count;
    }

}
