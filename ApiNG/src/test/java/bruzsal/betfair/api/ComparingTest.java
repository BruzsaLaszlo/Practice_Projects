package bruzsal.betfair.api;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

class ComparingTest {


    record T(int s, int i) implements Comparable<T> {

        @Override
        public int compareTo(@NotNull ComparingTest.T o) {
            int k = Integer.compare(s, o.s);
            return k == 0 ? Integer.compare(i, o.i) : k;
        }


    }

    static Random random = new Random();
    static final int MAX = 1_000_000;
    static List<T> list;


    @BeforeAll
    static void beforeAll() {
        assert Integer.MIN_VALUE - 1 == Integer.MAX_VALUE;
        System.out.println();
        list = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            list.add(new T(
                    random.nextInt(),
                    random.nextInt()));
        }
    }

    @Test
    void sortTestOwnComparator() {
        var own = list.stream()
                .sorted()
                .toList();
    }

    @Test
    void _sortArraysort() {
        new ArrayList<>(list).sort(T::compareTo);
    }

    @Test
    void sortTest() {
        var expected = list.stream()
                .sorted(comparing(T::s).thenComparing(T::i).reversed())
                .toList();
    }

    @Test
    void esortTest2() {
        var actual = list.stream()
                .sorted(comparing(T::i))
                .sorted(comparing(T::s).reversed())
                .toList();
    }


}
