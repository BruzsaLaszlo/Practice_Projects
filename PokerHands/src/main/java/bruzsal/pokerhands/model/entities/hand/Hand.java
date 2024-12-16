package bruzsal.pokerhands.model.entities.hand;

import bruzsal.pokerhands.model.entities.card.Card;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "hand")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hand {

    @Id
    private String id;

    @Column(nullable = false)
    private String gameId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Card> hole;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Card> flop;

    @OneToOne(cascade = CascadeType.ALL)
    private Card turn;

    @OneToOne(cascade = CascadeType.ALL)
    private Card river;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int blind;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private Streets foldedStreet;

    @Column(nullable = false)
    private int chips;

    @Column(nullable = false)
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
