import hackerrank.JavaStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaStackTest {

    @Test
    void isBalanced() {

        final String input = """
                {}()
                ({()})
                {}(
                []""";
        final String expectedOutput = """
                true
                true
                false
                true""";

        assertEquals(expectedOutput, new JavaStack().isBalanced(input));

        final String input2 = """
                {}()
                ({(})
                {}(
                []
                [
                ]""";
        final String expectedOutput2 = """
                true
                false
                false
                true
                false
                false""";

        assertEquals(expectedOutput2, new JavaStack().isBalanced(input2));
    }
}