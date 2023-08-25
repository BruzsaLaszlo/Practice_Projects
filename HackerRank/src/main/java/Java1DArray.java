import java.util.*;

public class Java1DArray {


    public static boolean canWin(int leap, int[] game) {

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int pos = stack.pop();

            if (pos >= game.length) return true;
            if (pos < 0 || game[pos] != 0) continue;

            game[pos] = -1;

            stack.addAll(new HashSet<>(Arrays.asList(pos + 1, pos + leap, pos - 1)));
        }
        return false;

    }

    public String test(String input) {
        StringBuilder sb = new StringBuilder();
        Scanner scan = new Scanner(input);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            sb.append(canWin(leap, game) ? "YES" : "NO").append("\n");
        }
        scan.close();
        return sb.toString();
    }

}
