package model.entities.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class Card implements Comparable<Card> {

    private final char name;
    private final CardColor color;

    public int getRank() {
        return CardRank.getRank(name);
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Card card && card.name == this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(getRank(), o.getRank());
    }
}
