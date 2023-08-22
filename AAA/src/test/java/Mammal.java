import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Mammal {
    public Mammal() {
    }

    @Test
    void regexp() {
        String s = "+6+++";
        System.out.println(s);
        System.out.println(s.matches("\\D$"));
        System.out.println("6+++".matches("[^0-9]$"));
        System.out.println(s.toString().matches("[^0-9]$"));

        String regExp = "\\d+|\\d+\\+\\d+|\\d+\\+\\d+\\+\\d+|\\d+\\+|\\d+\\+\\d+\\+";
        assertEquals(true, "6".matches(regExp));
        assertEquals(false, "+".matches(regExp));
        assertEquals(true, "6+6".matches(regExp));
        assertEquals(false, "6++".matches(regExp));
        assertEquals(false, "6+++".matches(regExp));
        assertEquals(false, "6++6+".matches(regExp));
        assertEquals(false, "6++6".matches(regExp));
        assertEquals(true, "6+6+".matches(regExp));
        assertEquals(true, "6+6+6".matches(regExp));
        assertEquals(true, "6+".matches(regExp));

        regExp = "\\d+|(\\d+\\+)*|(\\d+\\+\\d+)*";
        assertEquals(true, "6".matches(regExp));
        assertEquals(false, "+".matches(regExp));
        assertEquals(true, "6+6".matches(regExp));
        assertEquals(true, "6+6+6".matches(regExp));
        assertEquals(false, "6++".matches(regExp));
        assertEquals(false, "6+++".matches(regExp));
        assertEquals(false, "6++6+".matches(regExp));
        assertEquals(false, "6++6".matches(regExp));
        assertEquals(true, "6+6+".matches(regExp));
        assertEquals(true, "6+".matches(regExp));
    }
}
