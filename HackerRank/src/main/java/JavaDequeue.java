import java.util.*;

public class JavaDequeue {

    public int getMaximumNumberOfUniqueIntegers(String input) {
        Scanner sc = new Scanner(input);
        int n = sc.nextInt();
        int m = sc.nextInt();

        Deque<Integer> deque = new ArrayDeque<>(n);
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            deque.offer(num);
            set.add(num);
            if (i >= m - 1) {
                if (set.size() > max) max = set.size();
                int polled = deque.poll();
                if (!deque.contains(polled))
                    set.remove(polled);
            }
        }

        System.out.println(max);
        return max;
    }

}
