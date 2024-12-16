package hackerrank;

import java.util.ArrayList;
import java.util.Scanner;

public class JavaList {

    public String getInput(String input) {
        Scanner sc = new Scanner(input);

        int count = sc.nextInt();
        sc.nextLine();
        var list = new ArrayList<Integer>();

        for (int i = 0; i < count; i++) {
            list.add(sc.nextInt());
        }
        sc.nextLine();

        int queries = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < 2 * queries; i++) {
            String s = sc.nextLine();
            if (s.equals("Insert")) {
                list.add(sc.nextInt(), sc.nextInt());
            } else if (s.equals("Delete")) {
                list.remove(sc.nextInt());
            } else {
                break;
            }

            if (sc.hasNextLine()) {
                sc.nextLine();
            } else {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1)
                sb.append(" ");
        }
        return sb.toString();
    }
}