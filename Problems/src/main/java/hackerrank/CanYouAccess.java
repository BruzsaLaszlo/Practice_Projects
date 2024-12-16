package hackerrank;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class CanYouAccess {

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int num = Integer.parseInt(br.readLine().trim());
        int num = 7;
        Object o;// Must be used to hold the reference of the instance of the class Solution.Inner.Private

        Class<?> priv = Inner.class.getDeclaredClasses()[0];
        Constructor<?> constructor = priv.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        o = constructor.newInstance(new Inner());

        Method pow = o.getClass().getDeclaredMethods()[0];
        pow.setAccessible(true);
        System.out.println(num + " is " + pow.invoke(o, num));

        System.out.println("An instance of class: " + o.getClass().getCanonicalName() + " has been created");
    }//end of main

    static class Inner {
        private class Private {
            private String powerof2(int num) {
                return ((num & num - 1) == 0) ? "power of 2" : "not a power of 2";
            }
        }
    }//end of Inner

}//end of Solution



