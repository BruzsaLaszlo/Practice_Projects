import hackerrank.JavaDequeue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaDequeueTest {

    @Test
    void getMaximumNumberOfUniqueIntegers() {
        var input = """
                6 3
                5 3 5 2 3 2""";
        var expectedOutput = 3;

        assertEquals(expectedOutput, new JavaDequeue().getMaximumNumberOfUniqueIntegers(input));
    }
}