package model.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import model.entities.hand.Hand;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

@Getter
@Setter
@RequiredArgsConstructor
public class Game {

    public Game(List<Hand> hands) {
        this.hands = hands;
        id = hands.getFirst().getGameId();
    }

    private final String id;
    private List<Hand> hands;
    private LocalDateTime startTime;
    private int playersCount;
    private int finishedPlace;
    private double totalPrizePool;
    private double winningPrize;
    private double buyIn;
    private String name;
    private String description;

    public List<Hand> getHands() {
        return hands == null ? emptyList() : unmodifiableList(hands);
    }
}
