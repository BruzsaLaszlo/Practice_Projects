import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Java1DArrayTest {

    @Test
    void test0() {

        var input = """
                4
                5 3
                0 0 0 0 0
                6 5
                0 0 0 1 1 1
                6 3
                0 0 1 1 1 0
                3 1
                0 1 0""";
        var output = """
                YES
                YES
                NO
                NO
                """;

        assertEquals(output, new Java1DArray().test(input));

    }

    @Test
    void test1() throws IOException {
        Path inputFilePath = Paths.get("src/main/resources/Java1DArray/test1input.txt");
        String input = Files.readString(inputFilePath);
        Path outputFilePath = Paths.get("src/main/resources/Java1DArray/test1output.txt");
        String output = Files.readString(outputFilePath);

        assertEquals(output, new Java1DArray().test(input));
    }

    @Test
    void test1523() {
        var input = """
                1
                83 15
                0 0 0 1 0 1 0 1 1 1 0 1 0 1 0 0 0 0 1 1 1 0 0 1 1 1 1 0 0 0 0 1 0 0 0 1 0 0 0 1 1 1 1 0 0 1 0 0 1 0 1 0 0 1 1 0 1 0 0 1 0 0 0 1 1 1 0 1 1 0 0 1 1 1 1 0 0 1 0 0 1 0 0""";
        var output = "YES\n";
        assertEquals(output, new Java1DArray().test(input));
    }

    @Test
    void minValue() {
        assertEquals(Integer.MIN_VALUE, Integer.MAX_VALUE + 1);
        assertEquals(Integer.MIN_VALUE, Math.abs(Integer.MIN_VALUE));
    }

    @Test
    void powerOf() {
        assertEquals(16, 4 & 3);
    }
}