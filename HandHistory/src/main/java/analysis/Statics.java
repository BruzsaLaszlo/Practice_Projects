package analysis;

import lombok.RequiredArgsConstructor;
import model.entities.card.Card;
import model.entities.hand.Hand;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static model.entities.card.CardRank.getRank;

@RequiredArgsConstructor
public class Statics {

    private final List<Hand> hands;

    public int getHandsCount() {
        return hands.size();
    }

    public List<Hand> getHands() {
        return Collections.unmodifiableList(hands);
    }

    public Map<Character, Long> getHoleCardCountStat() {
        return hands.stream()
                .flatMap(hand -> hand.getHole().stream())
                .collect(groupingBy(Card::getName, counting()));
    }

    public long getHoleCardsAtLeastFrom(char name) {
        return hands.stream()
                .filter(hand -> isAtLeastRank(hand, name))
                .count();
    }

    public long getPairCount(Character... names) {
        return hands.stream()
                .filter(this::isPair)
                .filter(hand -> anyMatch(hand, names))
                .count();
    }

    public long getConnectorsCountFrom(char name) {
        return hands.stream()
                .filter(this::isConnector)
                .filter(hand -> isAtLeastRank(hand, name))
                .count();
    }

    public long getSuitedConnectorsCountFrom(char name) {
        return hands.stream()
                .filter(this::isSuited)
                .filter(this::isConnector)
                .filter(hand -> isAtLeastRank(hand, name))
                .count();
    }

    public boolean isSuited(Hand hand) {
        return hand.getHole().getFirst().getColor() == hand.getHole().getLast().getColor();
    }

    public boolean isConnector(Hand hand) {
        return Math.abs(hand.getHole().getFirst().getRank() - hand.getHole().getLast().getRank()) == 1;
    }

    public boolean isAtLeastRank(Hand hand, char name) {
        return Math.min(hand.getHole().getFirst().getRank(), hand.getHole().getLast().getRank()) >= getRank(name);
    }

    public boolean isPair(Hand hand) {
        return hand.getHole().getFirst().getName() == hand.getHole().getLast().getName();
    }

    public boolean isContains(Hand hand, char name) {
        return name == hand.getHole().getFirst().getName() || name == hand.getHole().getLast().getName();
    }

    public boolean anyMatch(Hand hand, Character... names) {
        if (names.length == 0) return true;
        else return Arrays.stream(names).anyMatch(name -> isContains(hand, name));
    }

}
