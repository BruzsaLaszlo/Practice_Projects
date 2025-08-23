package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberTest {

    @Test
    void szamjegyek() {
        final int number = 123456789;
        assertEquals(9, number % 10);
        int n = number & 0x1010;
//        assertEquals(8, number / 10);
        n = number;
        int i = 0;
        while (n != 0) {
            int d = n % (i++ * 10) / (i * 10);
            n /= 10;
            System.out.println(d);
        }
    }

    @Test
    void name() {
        int i = 0;
        assertEquals(0, i++);
        assertEquals(1, i++);
        assertEquals(2, i);
        assertEquals(6, i++ + ++i);
        assertEquals(4, i);
        assertEquals(4, i++ + i++ - 1);

        int d = i;
        assertEquals(i++ + ++i, ++d + d++);
    }

    @Test
    void integerMinValue() {
        assertEquals(Integer.MIN_VALUE, -Integer.MIN_VALUE);
    }

    @Test
    void stringTest() {
        String s = "Hello World".replaceAll(".*", "");
        assertEquals("", s);
    }

    @Test
    void integerEquals() {
        int n = -128;
        while (Integer.valueOf(++n) == Integer.valueOf(n)) {
        }
        ;
        System.out.println(n);
    }

    @Test
    void nullTest() {
        Object s = null;
        assertEquals(null, (String) s);
    }

    @Test
    void unary() {
        assertEquals(-1, ~0);
        assertEquals(-18, ~17);
        assertEquals(18, +-~17);

        int x = 10;
        int y = x++ + x;
        assertEquals(21, y);

        x = 5;
        y = 10;
        int z = ++x * y--;
        assertEquals(60, z);

    }

    @Test
    void testOrder() {
        class A {
            static int n;
            final int i;

            public A() {
                i = ++n;
            }

            public int getI() {
                System.out.println("A:" + i);
                return i;
            }
        }
        class B {
            static int n;
            final int i;

            public B() {
                i = ++n;
            }

            public int getI() {
                System.out.println("B:" + i);
                return i;
            }
        }

        int d = new A().getI() * (new B().getI() + new B().getI());
        assertEquals(4, d);
    }
}
