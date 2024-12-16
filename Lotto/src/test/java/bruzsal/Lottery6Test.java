package bruzsal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Lottery6Test {

    private static final int MAX_NUMBER = 45;
    static Lottery lottery;

    @BeforeAll
    static void readFromFile() {
        lottery = new Lottery();
        Path nonExist = Path.of("non-exist");
        assertThatThrownBy(() -> lottery.readFromFile(nonExist))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not read");

        Path path = Path.of("src/test/resources/hatos.csv");
        lottery.readFromFile(path);
        assertFalse(lottery.getSorsolasok(6).isEmpty());

    }

    @Test
    void refreshFromInternet() {
        lottery.refreshFromInternet("https://bet.szerencsejatek.hu/cmsfiles/hatos.csv");
        assertFalse(lottery.getSorsolasok(6).isEmpty());
    }

    @Test
    void orderNumberPulledOutCount() {
        Map<Integer, Integer> numberStat = lottery.getNumberStat();
        assertThat(numberStat).hasSize(MAX_NUMBER);

        LinkedHashMap<Integer, Integer> sorted = numberStat.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .collect(LinkedHashMap::new,
                        (linkedHashMap, entry) -> linkedHashMap.put(entry.getKey(), entry.getValue()),
                        LinkedHashMap::putAll);
//                        Map.Entry::getKey,Map.Entry::getValue, (integer, integer2) -> integer,LinkedHashMap::new));
        sorted.forEach((number, count) -> System.out.println(number + " -> " + count));
    }

    @Test
    void getNumberStat() {
        Map<Integer, Integer> numberStat = lottery.getNumberStat();
        assertThat(numberStat).hasSize(MAX_NUMBER);

        numberStat.forEach((number, count) -> System.out.println(number + " -> " + count));
    }

    @Test
    void isNumberPulledOut() {
        assertFalse(lottery.isNumberPulledOut(List.of(2, 9, 27, 31, 37, 41)));
        assertFalse(lottery.isNumberPulledOut(List.of(4, 17, 19, 27, 29, 42)));
        assertTrue(lottery.isNumberPulledOut(List.of(8, 12, 15, 20, 21, 28)));
    }

    @Test
    void isSamePulledOut() {
        assertFalse(lottery.isSamePulledOut());
    }

    @Test
    void isNumberPartiallyPulledOut() {
        assertThat(lottery.isNumberPartiallyPulledOut(List.of(8, 12, 15, 20, 21, 28), 6))
                .hasSize(1)
                .first()
                .extracting("year", "week")
                .containsExactly(2024, 17);
    }

    @Test
    void checkMyNumbers() {
        List<WinningNumbers> pulledOut = lottery.isNumberPartiallyPulledOut(List.of(40, 41, 42, 43, 44, 45), 4);
        System.out.println(pulledOut.size());
        pulledOut.forEach(sorsolas -> System.out.println(sorsolas.getNumbers()));

        System.out.println();

        List<WinningNumbers> pulledOut1 = lottery.isNumberPartiallyPulledOut(List.of(2, 9, 27, 31, 37, 41), 4);
        System.out.println(pulledOut1.size());
        pulledOut1.forEach(sorsolas -> System.out.println(sorsolas.getNumbers()));

        System.out.println();

        List<WinningNumbers> pulledOut2 = lottery.isNumberPartiallyPulledOut(List.of(4, 17, 19, 27, 29, 42), 4);
        System.out.println(pulledOut2.size());
        pulledOut2.forEach(sorsolas -> System.out.println(sorsolas.getNumbers()));
    }
}