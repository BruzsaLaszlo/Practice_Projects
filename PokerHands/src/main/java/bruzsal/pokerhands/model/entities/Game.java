package bruzsal.pokerhands.model.entities;

import bruzsal.pokerhands.model.entities.hand.Hand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

@Entity
@Table(name = "game")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {

    @Id
    private String id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Hand> hands;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private int playersCount;

    @Column(nullable = false)
    private int finishedPlace;

    @Column(nullable = false)
    private double totalPrizePool;

    @Column(nullable = false)
    private double winningPrize;

    @Column(nullable = false)
    private double buyIn;

    @Column(nullable = false)
    private String name;

    private String description;

    public Game(List<Hand> hands) {
        this.hands = hands;
        id = hands.getFirst().getGameId();
    }

    public Game(String id) {
        this.id = id;
    }

    public List<Hand> getHands() {
        return hands == null ? emptyList() : unmodifiableList(hands);
    }
}
