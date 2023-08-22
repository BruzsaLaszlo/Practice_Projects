import java.util.HashSet;
import java.util.Scanner;

public class JavaHashset {

    public String getNumberOfUniquPairs(String input) {
        Scanner sc = new Scanner(input);
        int count = sc.nextInt();
        sc.nextLine();
        StringBuilder output = new StringBuilder();

        var pairs = new HashSet<String>();
        for (int i = 1; i <= count; i++) {
            pairs.add(sc.nextLine());
            output.append(pairs.size());
            if (i != count) {
                output.append("\n");
            }
        }

        System.out.println(output);
        return output.toString();
    }

}
