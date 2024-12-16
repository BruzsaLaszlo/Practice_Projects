package bruzsal.pokerhands.model.entities.card;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "card")
@NoArgsConstructor
@Getter
@Setter
public class Card implements Comparable<Card> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private char name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardColor color;

    public Card(char name, CardColor color) {
        this.name = name;
        this.color = color;
    }

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
