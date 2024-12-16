package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaMap {

    public String getPhoneNumbers(String input) {
        Scanner sc = new Scanner(input);

        int count = sc.nextInt();
        sc.nextLine();

        Map<String, String> phoneBook = new HashMap<>(count);
        for (int i = 0; i < count; i++) {
            phoneBook.put(sc.nextLine(), sc.nextLine());
        }

        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            String name = sc.nextLine();
            String phone = phoneBook.get(name);
            if (phone == null) {
                sb.append("Not found");
            } else {
                sb.append(name).append("=").append(phone);
            }
            if (sc.hasNextLine())
                sb.append("\n");
        }

        System.out.println(sb);

        return sb.toString();
    }

}
