package hackerrank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JavaExceptionHandling {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            System.out.println(x / y);
        } catch (InputMismatchException im) {
            System.out.println(new InputMismatchException());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    class A {
        int m(int x) {
            return x;
        }
    }

    class B extends A {
        int m(int x) {

            return x;
        }
    }

}
