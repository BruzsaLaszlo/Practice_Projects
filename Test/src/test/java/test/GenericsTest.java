package test;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


class GenericsTest {

    static class A {
        static int a;

        public A() {
            a++;
        }
    }

    static class B extends A {
        @Override
        public String toString() {
            return String.valueOf(a);
        }
    }

    @Test
    void PECS() {
        List<? super B> list = getList(List.of(new A(), new A(), new B()));
        list.forEach(System.out::println);
        assertFalse(list.isEmpty());
    }

    List<? super A> getList(List<A> list) {
        return list.stream()
                .peek(System.out::println)
                .map(b -> (A) b)
                .toList();
    }
}
