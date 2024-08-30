package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

}
