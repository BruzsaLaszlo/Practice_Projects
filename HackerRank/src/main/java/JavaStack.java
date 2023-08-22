import java.util.*;

public class JavaStack {

    private static final Map<Character, Character> parenthesis = Map.of(
            '(', ')',
            '{', '}',
            '[', ']');

    public String isBalanced(String input) {
        Scanner sc = new Scanner(input);
        StringBuilder sb = new StringBuilder();

        while (sc.hasNextLine()) {
            var line = sc.nextLine();
            Deque<Character> stack = new ArrayDeque<>();

            boolean isBalanced = true;
            for (Character c : line.toCharArray()) {

                if (parenthesis.containsKey(c)) {
                    stack.push(c);
                } else {
                    try {
                        isBalanced = parenthesis.get(stack.pop()).equals(c);
                    } catch (NoSuchElementException nsee) {
                        isBalanced = false;
                    }
                }

                if (!isBalanced) {
                    break;
                }
            }

            sb.append(isBalanced && stack.isEmpty());
            if (sc.hasNextLine()) sb.append("\n");
        }

        System.out.println(sb);
        return sb.toString();

    }

}
