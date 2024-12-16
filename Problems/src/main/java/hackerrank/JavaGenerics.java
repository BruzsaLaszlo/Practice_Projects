package hackerrank;

import java.lang.reflect.Method;

public class JavaGenerics {

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};
        new Printer<Integer>().printArray(intArray);
        new Printer<String>().printArray(stringArray);
        int count = 0;

        for (Method method : Printer.class.getDeclaredMethods()) {
            String name = method.getName();

            if (name.equals("printArray"))
                count++;
        }

        if (count > 1) System.out.println("Method overloading is not allowed!");

    }

    private static class Printer<T> {

        public void printArray(T[] array) {
            for (T t : array) {
                System.out.println(t);
            }
        }
    }
}
