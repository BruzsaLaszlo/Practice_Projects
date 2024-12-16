package hackerrank;

import java.util.BitSet;
import java.util.Scanner;

public class JavaBitSet {

    public String bitOperators(String input) {
        Scanner sc = new Scanner(input);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        BitSet b1 = new BitSet(n);
        BitSet b2 = new BitSet(n);

        for (int i = 0; i < m; i++) {
            String[] s = sc.nextLine().split(" ");
            BitSet bs1 = s[1].equals("1") ? b1 : b2;
            BitSet bs2 = s[1].equals("2") ? b1 : b2;

            switch (s[0]) {
                case "AND" -> bs1.and(bs2);
                case "OR" -> bs1.or(bs2);
                case "XOR" -> bs1.xor(bs2);
                case "FLIP" -> bs1.flip(Integer.parseInt(s[2]));
                case "SET" -> bs1.set(Integer.parseInt(s[2]));
            }
            System.out.println(b1.cardinality() + " " + b2.cardinality());
        }

        return null;
    }

}
