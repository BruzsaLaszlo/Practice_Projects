package model.entities.hand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entities.card.Card;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hand {


    private List<Card> hole;
    private List<Card> flop;
    private Card turn;
    private Card river;

    private String id;
    private String gameId;
    private int level;
    private int blind;
    private LocalDateTime dateTime;

    private Streets foldedStreet;
    private int chips;
    private int chipsWon;

    public boolean isWon() {
        return chipsWon != 0;
    }

    public boolean isFolded() {
        return foldedStreet != null;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hand hand)) return false;
        return Objects.equals(id, hand.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
