package model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CardRank {

    public static final Map<Character, Integer> ranks;
    public static final Map<Integer, Character> reverseRank;

    static {
        ranks = new LinkedHashMap<>();
        for (int i = 2; i <= 9; i++) {
            ranks.put((char) (i + '0'), i);
        }
        ranks.put('T', 10);
        ranks.put('J', 11);
        ranks.put('Q', 12);
        ranks.put('K', 13);
        ranks.put('A', 14);
        reverseRank = ranks.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public static int getRank(char name) {
        return ranks.get(name);
    }

    public static char getReverseRank(int rank) {
        return reverseRank.get(rank);
    }

}
