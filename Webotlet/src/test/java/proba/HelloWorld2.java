package proba;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * shortcuts :
 * F7,
 * CTRL + W,
 * CTRL + SHIFT + /
 */

public class HelloWorld2 {

    Integer[] it = new Integer[100_000_000];
    private long counter;

    public static void main(String[] args) {


//        System.out.println(ClassLayout.parseClass(BooleanWrapper.class).toPrintable());
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        //list.stream().collect(Collectors.joining());
        int b = -38 % 3;
        System.out.println(b == 2);
        int x=5; x =  x + (x - ++x) * x++;
        System.out.println(x);
        int y = 2;
        int z = 5 + (6 - 4) * y++ - 2 + y++ + 1;
        System.out.println(x + " " + z);

        int number = new Scanner(System.in).nextInt();
        System.out.println(number);

       /* int tilde = 10;
        System.out.println(~tilde);

        HelloWorld2 helloWorld = new HelloWorld2();
        helloWorld.main();

        func f = Integer::compare;
        Consumer s = System.out::println;*/


    }

    static void toOut(Integer integer) {
        System.out.println(integer);
    }

    public void fax(Integer i1, int i2) {
    }

    boolean comp(int i) {
        return true;
    }

    private void cc(int i) {
        it[i] = i + 1;
    }

    void main() {

        int u = 8;


        for (int i = 0; i < 100_000_000; i++) {
            it[i] = Integer.valueOf(i);
        }

        long l = System.nanoTime();

        Integer icast = 0;
        for (int i : it) {
            cc(i);
        }

        icast = 100;

//        for (int i = 0; i < 1000_000_000; i++) {
//            int pp = i;
//        }

        System.out.println((System.nanoTime() - l) / 1000);
        System.exit(0);

        Integer iiii = 99;
        int iii = iiii;

        System.out.println("Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune".toUpperCase());

        HelloWorld2 helloWorld = new HelloWorld2();

        //System.out.println("Hello World!");
        List<Integer> i = Arrays.asList(2, 3, 5);
        List<Integer> ii = List.of(1, 2, 3);
        //i.add(3);
//        ii.add(4);


        Integer inti = 3;
        ArrayList<Integer> list = new ArrayList<Integer>(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4444);

        Collections.sort(list,
                Integer::compare);

        list.forEach(System.out::println);

        list.removeIf(inti::equals);

        System.out.println(list);


        List<String> personList = new ArrayList<>();

        // Add Elements
        personList.add("vicky");
        personList.add("poonam");
        personList.add("sachin");

        // Method reference to String type
        Collections.sort(personList,
                (obj, args) -> {
                    return 0;
                });
        personList.forEach(System.out::println);

        Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(21);
        streamIterated.filter((a) -> {
            return a % 5 == 0;
        }).forEach(System.out::println);

        Random random = new Random();
        DoubleStream doubleStream = random.doubles(3);
        doubleStream.forEach(System.out::println);

        Stream<String> streamOfString =
                Pattern.compile(", ").splitAsStream("a, b, c");
        streamOfString.forEach(System.out::println);

        List<String> elements =
                Stream.of("a", "b", "c").filter(element -> element.contains("b"))
                        .collect(Collectors.toList());

        Optional<String> anyElement = elements.stream().findAny();
        Optional<String> firstElement = elements.stream().findFirst();

        boolean c = false;
        boolean b = true;
        b ^= c ^ c;

        Stream.of("abcd", "bbcd", "cbcd").skip(1).map(element -> element.substring(0, 3)).forEach(System.out::println);


        List<String> list2 = new ArrayList<>();
        list2.add("123456");
        list2.add("123456");
        list2.add("123456");
        list2.add("123456");
        list2.add("123456");
        list2.stream().skip(1)
                .map(element -> element.substring(0, 3)).sorted().forEach(System.out::println);
        System.out.println(list2);

        System.out.println("============================================");

        List<String> list3 = Arrays.asList("abc1", "abc2", "abc3");
        counter = 0;
        Stream<String> stream = list3.stream().filter(element -> {
            wasCalled();
            return element.contains("2");
        });
        System.out.println(counter);

    }

    private void wasCalled() {
        counter++;
    }

    interface func {
        void valami(Integer integer, int i);
    }
}
