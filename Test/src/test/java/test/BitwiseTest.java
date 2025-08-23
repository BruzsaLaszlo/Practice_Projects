package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BitwiseTest {

    @Test
    void andPlus() {
        int buffer = 0;
        byte b = 1;
        assertEquals(b, b & 0xFF);
        buffer |= b & 0xFF;
        assertEquals(b, buffer);
        assertEquals(b, buffer | b & 0xFF);
        buffer <<= 8;
        buffer |= b & 0xFF;
        assertEquals(257, buffer);
    }

    @Test
    void andMinus() {
        int buffer = 0;
        byte b = -1;
        assertEquals(0xFF_FF_FF_FF, (int) b);
        buffer |= b & 0x00_00_00_FF;
        assertEquals(255, buffer);
        assertEquals(255, buffer | b & 0xFF);
        buffer <<= 8;
        buffer |= b & 0xFF;
        assertEquals(65535, buffer);
    }


}