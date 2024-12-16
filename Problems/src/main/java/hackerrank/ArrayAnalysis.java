package hackerrank;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static hackerrank.ArrayAnalysis.Binary.ONE;
import static hackerrank.ArrayAnalysis.Binary.ZERO;
import static hackerrank.ArrayAnalysis.Ch.DIGIT;
import static hackerrank.ArrayAnalysis.Ch.LETTER;
import static java.lang.Character.isDigit;
import static java.lang.Integer.numberOfLeadingZeros;
import static java.lang.Integer.numberOfTrailingZeros;
import static java.lang.Math.min;

public class ArrayAnalysis {

    public record Pair(int a, int b) {
    }

    @Getter
    private int methodCall;

    @Getter
    private final List<Integer> listN = new ArrayList<>();

    private int countIterate;

    public enum Ch {DIGIT, LETTER}

    public enum Binary {ONE, ZERO}

    public Pair identifySubarrayOn22(int b, int bitcount) {
        methodCall++;
        countIterate = 0;

        int one = Integer.bitCount(b);
        int zero = bitcount - one;
        final Binary most = one > zero ? ONE : ZERO;

        int start = 0;
        int end = bitcount;
        boolean isFirstOne;
        boolean isLastOne;

        while (start < end) {
            countIterate++;

            if (one == zero) {
                listN.add(countIterate);
                return new Pair(start, --end);
            }

            isFirstOne = numberOfLeadingZeros(b) == 32 - bitcount + start;
            isLastOne = numberOfTrailingZeros(b) == bitcount - end;

            if (isFirstOne == isLastOne) {
                if (isLastOne) one--;
                else zero--;
                end--;
            } else if (isLastOne && most == ONE) {
                one--;
                end--;
            } else if (!isLastOne && most == ZERO) {
                zero--;
                end--;
            } else if (isFirstOne) {
                one--;
                start++;
            } else {
                zero--;
                start++;
            }
        }
        listN.add(countIterate);
        return null;
    }

    public Pair identifySubarray(List<Character> array) {
        methodCall++;
        countIterate = 0;

        int digit = getCount(0, array.size(), array);
        int letter = array.size() - digit;
        final Ch most = digit > letter ? DIGIT : LETTER;

        int start = 0;
        int end = array.size() - 1;
        boolean isFirstDigit;
        boolean isLastDigit;

        while (start < end) {
            countIterate++;

            if (digit == letter) {
                listN.add(countIterate);
                return new Pair(start, end);
            }

            isFirstDigit = isDigit(array.get(start));
            isLastDigit = isDigit(array.get(end));

            if (isFirstDigit == isLastDigit) {
                if (isFirstDigit) digit--;
                else letter--;
                end--;
            } else if (isLastDigit && most == DIGIT) {
                digit--;
                end--;
            } else if (!isLastDigit && most == LETTER) {
                letter--;
                end--;
            } else if (isFirstDigit) {
                digit--;
                start++;
            } else {
                letter--;
                start++;
            }

        }

        listN.add(countIterate);
        return null;
    }

    public Pair identifySubarrayOn2(List<Character> array) {
        methodCall++;
        countIterate = 1;

        int count = getCount(0, array.size(), array);
        if (count == 0 || count == array.size() || array.size() < 2) {
            listN.add(1);
            return null;
        }
        int maxSize = min(count, array.size() - count) * 2;
        if (maxSize == array.size()) {
            listN.add(1);
            return new Pair(0, maxSize - 1);
        }

        Pair find = find(maxSize, array);
        listN.add(countIterate);
        return find;
    }

    private Pair find(int maxSize, List<Character> array) {
        for (int i = 0; i + maxSize <= array.size(); i++) {
            int count = getCount(i, i + maxSize, array);
            if (count * 2 == maxSize) {
                return new Pair(i, i + maxSize - 1);
            }
        }
        return find(maxSize - 1, array);
    }

    private int getCount(int min, int max, List<Character> array) {
        int count = 0;
        for (int i = min; i < max; i++) {
            countIterate++;
            if (isDigit(array.get(i))) count++;
        }
        return count;
    }

}
