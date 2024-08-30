package model;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static model.CardRank.getRank;

@RequiredArgsConstructor
public class Statics {

    private final List<Hand> hands;

    public int getHandsCount() {
        return hands.size();
    }

    public List<Hand> getHands() {
        return Collections.unmodifiableList(hands);
    }

    public Map<Card, Integer> getHoleCardCountStat() {
        return hands.stream()
                .flatMap(hand -> hand.getHole().stream())
                .collect(Collectors.toMap(card -> card, card -> 1, Integer::sum));
    }

    public long getHoleCardsAtLeastRank(char name) {
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

    public long getSuitedConnectorsCountFrom(char name) {
        return hands.stream()
                .filter(this::isSuited)
                .filter(this::isConnector)
                .filter(hand -> isAtLeastRank(hand, name))
                .count();
    }

    public long getConnectors() {
        return hands.stream()
                .filter(this::isConnector)
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
