package bruzsal.pokerhands.model.entities.card;

import lombok.Getter;

public enum CardColor {

    SPADES('s'), HEART('h'), DIAMOND('d'), CLUB('c');

    @Getter
    private final char shortName;

    CardColor(char shortName) {
        this.shortName = shortName;
    }

    public static CardColor parseFromShortName(char shortName) {
        for (CardColor color : CardColor.values()) {
            if (color.getShortName() == shortName) {
                return color;
            }
        }
        throw new IllegalArgumentException("Invalid card color: " + shortName);
    }
}
