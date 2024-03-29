import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.Month;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

class HackerRankTest {

    @Test
    void JavaDataTypes() {

        String in = new Scanner(System.in).nextLine();
        String out;
        final int count = Integer.parseInt(in);
        for (int i = 0; i < count; i++)
            try {
                in = new Scanner(System.in).nextLine();
                long l = Long.parseLong(in);
                out = "\n* long";
                if (l >= Integer.MIN_VALUE && l <= Integer.MAX_VALUE) {
                    out = "\n* int" + out;
                    if (l >= Short.MIN_VALUE && l <= Short.MAX_VALUE) {
                        out = "\n* short" + out;
                        if (l >= Byte.MIN_VALUE && l <= Byte.MAX_VALUE) {
                            out = "\n* byte" + out;
                        }
                    }
                }
                out = in + " can be fitted in:" + out;
                System.out.println(out);
            } catch (NumberFormatException exception) {
                System.out.println(in + " can't be fitted anywhere.");
            }
    }


    @Test
    void arraysAsList() {
        int[] arr = {1, 2, 3, 4, 5};
        var list = new ArrayList<>(Arrays.asList(arr));
//        Collections.addAll(list,arr);
        assertEquals(1, list.size());

        record R(int i, int k, int l) {
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                R r = (R) o;
                return i == r.i && k == r.k;
            }

            @Override
            public int hashCode() {
                return Objects.hash(i);
            }
        }

        Map<R, Integer> map = new HashMap<>();
        var r1 = new R(1, 2, 3);
        var r2 = new R(1, 2, 4);
        map.put(r1, 0);
        map.put(r2, 0);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r1.hashCode());
        assertEquals(1, map.size());
        assertTrue(map.containsKey(r1));
        assertTrue(map.containsKey(r2));
        assertEquals(3, map.keySet().stream().findFirst().get().l);

        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        list1.remove(0);
        assertEquals(2, list1.get(0));
    }

    public static long minimumPasses(long m, long w, long p, long n) {
        long candies = 0;
        long invest = 0;
        long spend = Long.MAX_VALUE;

        while (candies < n) {
            // preventing overflow in m*w
            long passes = (long) (((p - candies) / (double) m) / w);

            if (passes <= 0) {
                // machines we can buy in total
                long mw = candies / p + m + w;
                long half = mw >>> 1;
                if (m > w) {
                    m = Math.max(m, half);
                    w = mw - m;
                } else {
                    w = Math.max(w, half);
                    m = mw - w;
                }
                candies %= p;
                passes++;
            }

            // handling overflowing
            // if overflowing is encountered -> candies count are definitely more than long
            // thus it is more than n since n is long
            // so we've reached the goal and we can break the loop
            long mw;
            long pmw;
            try {
                mw = Math.multiplyExact(m, w);
                pmw = Math.multiplyExact(passes, mw);
            } catch (ArithmeticException ex) {
                // we need to add current pass
                invest += 1;
                // increment will be 1 because of overflow
                spend = Math.min(spend, invest + 1);
                break;
            }

            candies += pmw;
            invest += passes;
            long increment = (long) Math.ceil((n - candies) / (double) mw);
            spend = Math.min(spend, invest + increment);
        }

        return Math.min(spend, invest);
    }

    @Test
    void findPairs() {
        var arr = new int[]{7, 1, 5, 7, 3, 3, 5, 7, 6, 7};

        assertEquals(4, findPairs(arr));

        Arrays.sort(arr);
        assertEquals(4, findPairsRecurcion(arr));
    }

    public int findPairs(int[] arr) {
        return Arrays.stream(arr)
                .distinct()
                .mapToLong(o -> Arrays.stream(arr)
                        .filter(c -> c == o)
                        .count())
                .mapToInt(l -> (int) l / 2)
                .sum();
    }

    public int findPairsRecurcion(int[] arr) {
        if (arr.length > 1) {
            return switch (arr[0] - arr[1]) {
                case 0 -> 1 + findPairsRecurcion(Arrays.copyOfRange(arr, 2, arr.length));
                default -> findPairsRecurcion(Arrays.copyOfRange(arr, 1, arr.length));
            };
        }
        return 0;
    }

    @Test
    void subArrays() {
//        Scanner sc = new Scanner(System.in);
//        int length = Integer.parseInt(sc.nextLine());
        int[] arr = new int[]{1, -2, 4, -5, 1};
        int length = 5;
//        for (int i = 0; i < length; i++) {
//            arr[i] = sc.nextInt();
//        }


        int count = 0;
        for (int l = 0; l < length; l++) {
            for (int j = 0; j < length - l; j++) {
                int sum = 0;
                for (int k = j; k < l + j + 1; k++) {
                    sum += arr[k];
                }
                if (sum < 0) count++;
            }
        }

        System.out.println(count);
    }

    private class Node {
        Node parent;
        int bal;
        int jobb;
        int value;

        public Node(Node parent, int value) {
            this.parent = parent;
            this.value = value;
        }
    }

    public boolean canWin(int leap, int[] game) {
        int pos = 0;
        Node root = new Node(null, 0);
        Node actual = root;
        while (true) {
            if (game[pos + 1] == 0 || game[pos + leap] == 0) {
                if (game[pos + 1] == 0) actual.bal = pos + 1;
                else actual.bal = -1;
                if (game[pos + leap] == 0) actual.jobb = pos + leap;
                actual.jobb = -1;
            } else {
                if (actual.parent == null) return false;
                while (actual.jobb == 0 || actual.bal == 0)
                    actual = actual.parent;
            }

        }
    }

    @Test
    void testRegular() {
        String text =
                "This is the text to be searched " +
                        "for occurrences of the http:// pattern.";

        String patternString = "This is the";

        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        System.out.println("lookingAt = " + matcher.lookingAt());
        System.out.println("matches   = " + matcher.matches());
    }

    @Test
    void testcanwin() {
        assertTrue(canWin(3, new int[]{0, 0, 1, 1, 1, 0}));
    }

    @Test
    void sortTest() {

        record T(String s, int i, int k) {
            static int id;

            static {
                id++;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                T t = (T) o;
                return i == t.i;
            }

            @Override
            public int hashCode() {
                return Objects.hash(i);
            }
        }

        Random random = new Random();
        final int max = 100_000;
        List<T> list = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            int[] b = random.ints(5).toArray();
            list.add(new T(Arrays.toString(b), random.nextInt(), random.nextInt()));
        }

        var actual = list.parallelStream()
                .sorted(comparing(T::i))
                .sorted(comparing(T::s))
                .toList();

        var expected = list.parallelStream()
                .sorted(comparing(T::s).thenComparing(T::i))
                .toList();

        assertEquals(expected, actual);
//        actual.forEach(System.out::println);

    }

    @Test
    void testStringMethods() {
        String s = "Ez egy String (őŐűŰ)";
        System.out.println(s.toUpperCase());

    }

    @Test
    void testPath() {
        String s = "/output0.txt";
        String s2 = "output0.txt";
        System.out.println(Path.of(s).toAbsolutePath());
        System.out.println(Path.of(s2).toAbsolutePath());
        String s3 = "/proba.txt";
        System.out.println(Path.of(s3));
        URL url = getClass().getResource(s3);
        System.out.println(url);
        try (InputStream in = Properties.class.getResourceAsStream(s3)) {
            System.out.println(new BufferedReader(new InputStreamReader(in)).lines().collect(Collectors.joining("\n")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void gameArray() {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println((canWin(leap, game)) ? "YES" : "NO");
        }
        scan.close();
    }

    @Test
    void dynamicSizeArrays() {

        Scanner sc = new Scanner(System.in);
        int arrLength = Integer.parseInt(sc.nextLine());
        List<List<Integer>> list = new ArrayList<>(arrLength);
        for (int i = 0; i < arrLength; i++) {
            list.add(Arrays.stream(sc.nextLine().split(" "))
                    .skip(1)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList()));
        }

        int lengthQuery = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < lengthQuery; i++) {
            var index = sc.nextLine().split(" ");
            try {
                int value = list.get(Integer.parseInt(index[0]) - 1).get(Integer.parseInt(index[1]) - 1);
                System.out.println(value);
            } catch (Exception e) {
                System.out.println("ERROR!");
            }
        }

    }

    @Test
    void generateArray() {
        var ints = new Random()
                .ints(10000, 0, 100)
                .mapToObj(value -> String.valueOf(value))
                .collect(joining(","));
        System.out.println(ints);
    }

    @Test
    void minimumPasses() {
        assertEquals(16, minimumPasses(1, 1, 6, 45));
    }

    @Test
    void JavaEndOfFile() {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; sc.hasNext(); i++) {
            String line = sc.nextLine();
            System.out.println(i + ". " + line);
        }

    }

    @Test
    void JavaStaticInitializerBlock() {

        Scanner sc = new Scanner(System.in);

        int b = sc.nextInt();
        sc.nextLine();
        int h = sc.nextInt();

        if (b <= 0 || h <= 0) {
            System.out.println("java.lang.Exception: Breadth and height must be positive");
        } else {
            System.out.println(b * h);
        }

    }

    @Test
    void intToString() {

        int i = new Scanner(System.in).nextInt();

        if (i >= -100 && i <= 100) {
            System.out.println("Good job");
        } else {
            System.out.println("Wrong answer");
        }

    }

    @Test
    void JavaDateandTime() {

        int year = 2021;
        int month = 11;
        int day = 14;

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Month.of(month).getValue(), day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        String dayOfWeekString;
        switch (dayOfWeek) {
            case 1 -> dayOfWeekString = "Sunday";
            case 2 -> dayOfWeekString = "Monday";
            case 3 -> dayOfWeekString = "Tuesday";
            case 4 -> dayOfWeekString = "Wednesday";
            case 5 -> dayOfWeekString = "Thursday";
            case 6 -> dayOfWeekString = "Friday";
            case 7 -> dayOfWeekString = "Saturday";
            default -> dayOfWeekString = "";
        }

        System.out.println(dayOfWeekString.toUpperCase());

    }


    @Test
    void JavaCurrencyFortmatter() {

        double payment = 12324.134;

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

        System.out.println("US: " + nf.format(payment));

        Locale india = new Locale("en", "in");
        nf = NumberFormat.getCurrencyInstance(india);
        System.out.println("India: " + nf.format(payment));

        nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println("China: " + nf.format(payment));

        nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        System.out.println("France: " + nf.format(payment));

        String s = "093";
//        String pattern = "[2]*[5][0-5]|[2]*[0-4]\\d|[0-1]\\d\\d|\\d\\d|\\d";
        String one = "[2]*[5][0-5]|[2]*[0-4]\\d|[0-1]\\d{2}|\\d{1,2}";

        Pattern p = Pattern.compile(one, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(one);


        String pattern = one + "[.]" + one + "[.]" + one + "[.]" + one;
        assertTrue("093".matches(one));
        assertTrue("93".matches(one));
        assertTrue("3".matches(one));

        assertFalse("0001".matches(one));
        assertFalse("256".matches(one));
        assertFalse("-123".matches(one));


    }


    @Test
    void sdfsa() {
        // String to be scanned to find the pattern.
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(.*)(\\d+)(.*)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
        } else {
            System.out.println("NO MATCH");
        }
    }

    @Test
    void url() {

        String url = "https://earthquake.usgs.gov:80/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";

        String dp = ".*://(.+):.*\1";

        System.out.println(url.replaceFirst(dp, "sdfsf"));

        dp = "(\\w+)://([\\w.]+)(.*)";
        Pattern p = Pattern.compile(dp, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(url);


        assertTrue(m.matches());

        System.out.println("Found 0value: " + m.group(0));
        System.out.println("protocol: " + m.group(1));
        System.out.println("domain: " + m.group(2));
//        System.out.println("Found 3value: " + m.group(3) );

        p = Pattern.compile(":(\\d+)(.*)");
        m = p.matcher(m.group(3));
        assertTrue(m.matches());

        System.out.println("port: " + m.group(1));

//        p = Pattern.compile("/(.+)\\?(.*)");
//        m = p.matcher(m.group(2));
        assertTrue(Pattern.compile("/(.+)\\?(.*)").matcher(m.group(2)).matches());
        System.out.println("path: " + m.group(1));
        System.out.println("parameters: " + m.group(2));

//        if (m.group(3).startsWith(":"))
//            System.out.println(
//                    Pattern.compile(":(\\d+).*")
//                            .matcher(m.group(3))
//                            .group(0));


    }

    /**
     * Sum the lengths of A and B
     * Determine if A is lexicographically larger than B (i.e.: does B come before A in the dictionary?).
     * Capitalize the first letter in A and B and print them on a single line, separated by a space.
     */
    @Test
    void strings() {

        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        /* Enter your code here. Print output to STDOUT. */
        System.out.println(A.length() + B.length());

        System.out.println(A.compareTo(B) > 0 ? "Yes" : "No");

        System.out.println(A.toUpperCase().charAt(0) + A.substring(1) + " " + B.toUpperCase().charAt(0) + B.substring(1));

        Scanner in = new Scanner(System.in);
        String S = in.next();
        int start = in.nextInt();
        int end = in.nextInt();

        System.out.println(S.substring(start, end));


    }

    private String sucks(String s) {
        {
            char[] charArr = s.toCharArray();
            int n = charArr.length;
            int i = 0;
            for (i = n - 1; i > 0; i--) {
                if (charArr[i] > charArr[i - 1]) {
                    break;
                }
            }
            if (i == 0) {
                return "no answer";
            } else {
                int small = charArr[i - 1], next = i;

                for (int j = i + 1; j < n; j++) {
                    if (charArr[j] > small && charArr[j] < charArr[next]) {
                        next = j;
                    }
                }
                swap(charArr, i - 1, next);
                Arrays.sort(charArr, i, n);
            }
            return new String(charArr);
        }

    }

    void swap(char charArr[], int i, int j) {
        char temp = charArr[i];
        charArr[i] = charArr[j];
        charArr[j] = temp;
    }

    private String subStringArray(String w, String text) {
        var original = w.toCharArray();
        var sorted = text.toCharArray();
        var list = new ArrayList<char[]>();
        System.err.println(text);
        System.err.println(Arrays.toString(sorted));
        for (int i = 1; i < sorted.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                var cc = Arrays.copyOf(sorted, sorted.length);
                char cs = cc[i];
                cc[i] = cc[j];
                cc[j] = cs;
                System.out.println(Arrays.toString(cc));
                if (Arrays.compare(original, cc) < 0) {
//                    System.out.println(Arrays.toString(original));
//                    System.out.println(Arrays.toString(cc));
                    list.add(cc);
                }
            }
        }
        var min = list.stream().min(Arrays::compare);
        return min.map(String::valueOf).orElse("no answer");
    }

    @Test
    void biggerIsGreater() {
        String w = "d(4)k(11)h(8)c(3)";
        var list = new ArrayList<>();
//        for (char i = 'a'; i < 'z'; i++) {
//            System.out.println(i);
//        }

//        Collator c = Collator.getInstance(new Locale("hu"));
//        new ArrayList<String>().sort(String.CASE_INSENSITIVE_ORDER);

        w = "dhck";
        System.out.println(sucks(w));

        StringBuilder sb = new StringBuilder();
        w.chars().sorted().mapToObj(value -> (char) value).forEach(character -> sb.append(character));
        System.out.println(w);
        System.out.println(sb);

        var result = subStringArray(w, sb.toString());
        System.out.println(result);
        assertEquals("hcdk", result);

//        subs(w, w.length() - 2, 1);

//        System.out.println(van);
    }

    private String subs(String o, int where, int hm) {
        for (int i = 0; i < o.length() - hm; i++) {
            var s = o.substring(0, where - i) + o.substring(o.length() - hm) + o.substring(where - i, o.length() - hm);
            System.out.println(o.substring(0, where + 1));
            System.out.println(o.substring(o.length() - hm));
            System.out.println(o.substring(where + hm));
            if (o.compareTo(s) < 0) {
                System.out.println("result: " + s);
                return s;
            } else subs(o, --where, ++hm);
        }
        return "no answer";
    }

    @Test
    void encryption() {
        String s = "haveaniceday";
        double sqrt = Math.sqrt(s.length());
        int ceil = (int) Math.ceil(sqrt);
        int floor = (int) Math.floor(sqrt);
        var result = new StringBuilder();
        for (int i = 0; i < ceil; i++) {
            for (int j = i; j < s.length(); j += ceil) {
                result.append(s.charAt(j));
            }
            result.append(" ");
        }
        System.out.println(result);
    }

    @Test
    void bigInteger() {

        String n = "456345635464565665776767";
        BigInteger i = new BigInteger(n);
        System.out.println(i.isProbablePrime(10) ? "prime" : "not prime");
    }

    @Test
    void minMax() {
        List<Integer> arr = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.size() - 3; i++) {
            int sum = arr.get(i) + arr.get(i + 1) + arr.get(i + 2) + arr.get(i + 3);
            if (sum > max) max = sum;
            if (sum < min) min = sum;
        }
        System.out.println(min + " " + max);
    }

    @Test
    void straircase() {
        StringBuilder sb = new StringBuilder();
        int n = 10;
        for (int i = 0; i < n; i++) {
            sb.append(" ".repeat(n - 1 - i));
            sb.append("#".repeat(i + 1));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    @Test
    void timeConversion() {
        String s = "12:40:22AM";
        int hour = Integer.parseInt(s.substring(0, 2));
        if (s.endsWith("PM")) {
            if (hour != 12) {
                hour += 12;
            }
        } else {
            if (hour == 12) {
                hour = 0;
            }
        }
        s = String.format("%02d%s", hour, s.substring(2, 8));
        System.out.println(s);
    }

    @Test
    void candles() {
        List<Integer> candles = List.of(3, 2, 1, 3);
        int max = Integer.MIN_VALUE;
        long count = 0;
        for (int i = 0; i < candles.size(); i++) {
            int actual = candles.get(i);
            if (max < actual) {
                max = actual;
                count = 1;
            } else if (max == actual) {
                count++;
            }
        }
        System.out.println(count);

        count = candles.stream()
                .mapToInt(Integer::valueOf)
                .filter(integer -> integer == candles.stream().max(Integer::compareTo).get())
                .count();
        System.out.println(count);
    }

    @Test
    void minMax2() {
        List<Integer> arr = List.of(1, 2, 3, 4, 5);
        var result = new ArrayList<Long>();
        for (int i = 0; i < arr.size(); i++) {
            var list = new ArrayList<>(arr);
            list.remove(i);
            result.add(list.stream().mapToLong(Integer::valueOf).sum());
        }
        var stat = result.stream()
                .mapToLong(Long::longValue)
                .summaryStatistics();
        System.out.println(stat.getMin() + " " + stat.getMax());
    }

    @Test
    void plusMinus() {
        List<Integer> arr = List.of(-4, 3, -9, 0, 4, 1);
        int pos = 0;
        int neg = 0;
        int zero = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > 0) {
                pos++;
            } else if (arr.get(i) < 0) {
                neg++;
            } else {
                zero++;
            }
        }
        final double size = arr.size();
        assert zero == 1 : zero;
        var line = String.format("%.6f%n%.6f%n%.6f", pos / size, neg / size, zero / size);
        System.out.println(line);
    }

    @Test
    void diagonalDifference() {
        List<List<Integer>> arr = new ArrayList<>();
        int leftToRight = 0;
        int rightToLeft = 0;
        for (int i = 0; i < arr.size(); i++) {
            leftToRight += arr.get(i).get(i);
            rightToLeft += arr.get(i).get(arr.size() - 1 - i);
        }
        Math.abs(leftToRight - rightToLeft);
    }

    @Test
    void sum() {
        List<Integer> ar = List.of(1000000001, 1000000002, 1000000003, 1000000004, 1000000005);
        long l = ar.stream().mapToLong(Long::valueOf).reduce((integer, integer2) -> integer + integer2).getAsLong();
        System.out.println(l);
    }

    @Test
    void subArray() {

        int[] a = {1, -2, 4, -5, 1};

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {

            }
        }

    }

    @Test
    void hourGlass() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, 6).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int sum = arr.get(i).get(j) + arr.get(i).get(j + 1) + arr.get(i).get(j + 2)
                        + arr.get(i + 1).get(j + 1)
                        + arr.get(i + 2).get(j) + arr.get(i + 2).get(j + 1) + arr.get(i + 2).get(j + 2);
                if (sum > max) max = sum;
            }
        }
        System.out.println(max);
    }

    @Test
    void testLists() {
        int[] ints = {1, 2, 3, 4};

        var as = Arrays.asList(1, 2, 3, 4);
        var arr = new ArrayList<>(as);
        arr.add(5);
        assertTrue(as.contains(5));
        List.of(ints);
    }

    @Test
    void BigDecimalArray() {
        String[] s = {"9",
                "-100",
                "50",
                "0",
                "56.6",
                "90",
                "0.12",
                ".12",
                "02.34",
                "000.000"
                , null, null};
        //= new String[n + 2];

        s = Arrays.stream(Arrays.copyOf(s, s.length - 2))
                .sorted(comparing(BigDecimal::new, reverseOrder()))
                .toArray(String[]::new);

        System.out.println(Arrays.toString(s));
    }

    @Test
    void biginteger() {

        Scanner sc = new Scanner(System.in);
        BigInteger a = new BigInteger(sc.nextLine());
        BigInteger b = new BigInteger(sc.nextLine());
        System.out.println(a.add(b));
        System.out.println(a.multiply(b));
    }

    @Test
    void givenUsingCoreJava_whenListConvertedToArray_thenCorrect() {
        new ArrayList<>();
        List<Integer> sourceList = Arrays.asList(0, 1, 2, 3, 4, 5);
        Integer[] targetArray = sourceList.toArray(Integer[]::new);
        System.out.println(Arrays.toString(targetArray));

        String s = "abcde";
        Character[] chars = s.chars().mapToObj(value -> (char) value).toArray(Character[]::new);
        List<Character> charsList = s.chars().mapToObj(value -> (char) value).toList();
        System.out.println(charsList);

        record Employee(String name, long id, double salary, String division) {
        }
        var emps = List.of(
                new Employee("weqe", 1, 1.1, "S"),
                new Employee("e", 3, 1.2, "A"),
                new Employee("w", 2, 1.3, "B"),
                new Employee("w", 2, .3, "B"),
                new Employee("43", 5, 1.4, "S")
        );
        var list = new ArrayList<Employee>(emps);
        var ize = list.stream()
                .collect(groupingBy(
                        Employee::division,
                        mapping(Employee::salary, minBy(Double::compareTo))));
        System.out.println(ize);
    }

    @Test
    void tokens() {

        final String s = "He is a very very good boy, isn't he?";

        int testCases = 3;
        try {
            Pattern.compile("([A-Za-z])");
            System.out.println("Valid");
        } catch (PatternSyntaxException e) {
            System.out.println("Invalid");
        } finally {
            testCases--;
        }


        var data = s.split("[ !,?._'@]");
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (String ss : data)
            if (ss.length() > 0) {
                count++;
                sb.append(ss + '\n');
            }
        System.out.println(count + "\n" + sb);
    }

    @Test
    void isAnagram(String a, String b) {
        var aMap = getCharStat(a.toLowerCase());
        var bMap = getCharStat(b.toLowerCase());
        aMap.equals(bMap);


    }

    private Map<Integer, Integer> getCharStat(String a) {
        return a.chars()
                .collect(
                        TreeMap::new,
                        (treeMap, value) -> {
                            if (treeMap.containsKey(value)) {
                                treeMap.put(value, treeMap.get(value) + 1);
                            } else {
                                treeMap.put(value, 1);
                            }
                        },
                        TreeMap::putAll
                );
    }

    @Test
    void palindrome() {

        String s = "madam";
        boolean b = true;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                b = false;
                break;
            }
        }
        System.out.println(b ? "Yes" : "No");
    }

    public static String getSmallestAndLargest(String s, int k) {

        // Complete the function
        // 'smallest' must be the lexicographically smallest substring of length 'k'
        // 'largest' must be the lexicographically largest substring of length 'k'
        String smallest = "z";
        String largest = "A";
        for (int i = 0; i < s.length() - k + 1; i++) {
            String sub = s.substring(i, i + k);
            if (smallest.compareTo(sub) > 0) smallest = sub;
            if (largest.compareTo(sub) < 0) largest = sub;
        }

        return smallest + "\n" + largest;
    }

    @Test
    void ingatlanCom() throws URISyntaxException, IOException, InterruptedException {

        String url = "https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3";
//        String url = "https://datahub.io/core/country-list";
//        String url = "https://www.nationsonline.org/oneworld/country_code_list.htm";

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

    }

}
