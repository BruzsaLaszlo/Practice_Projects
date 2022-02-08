import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

public class TestFunctions {

    @Test
    void reduce() {
        var list = List.of(1, 1, 1, 1, 1);
        System.out.println(list.stream()
                .reduce(Integer::sum));
    }

    @Test
    void paralell() {
        Assertions.assertEquals(1, 2);
        Assertions.assertEquals(1, 3);
        Assertions.assertEquals(3, 4);
        Assertions.assertEquals(4, 5);
        var list = IntStream.range(0, 1000)
                .mapToObj(String::valueOf)
                .toList();
        var concated = list.stream()
                .parallel()
                .reduce("",
                        (s, s2) -> s.concat(s2) + " ",
                        (s, s2) -> s.concat(s2) + "\n");
        System.out.println(concated);
        IntStream.rangeClosed('a', 'z')
                .mapToObj(value -> (char) value)
                .forEach(System.out::println);
    }
}
