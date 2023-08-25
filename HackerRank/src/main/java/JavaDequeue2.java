import java.util.*;

public class JavaDequeue2 {

    public int getMaximumNumberOfUniqueIntegers(String input) {
        Scanner sc = new Scanner(input);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        int maxUnique = 0;
        Set<Integer> uniqueElements = new HashSet<>(m, 1f);
        // a sliding window that removes the need for a less effective interator
        Deque<Integer> slider = new ArrayDeque<>(m + 1);
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            slider.addLast(num);
            uniqueElements.add(num);
            if (slider.size() > m) {
                int removed = slider.removeFirst();
                if (!slider.contains(removed)) {
                    uniqueElements.remove(removed);
                }
            }
            maxUnique = Math.max(maxUnique, uniqueElements.size());
            if (uniqueElements.size() == m) break;
        }
        System.out.println(maxUnique);

        return maxUnique;
    }
}
