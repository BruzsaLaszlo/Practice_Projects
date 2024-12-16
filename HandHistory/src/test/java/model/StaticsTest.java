package model;

import analysis.AnalyseHand;
import analysis.Statics;
import model.entities.Game;
import model.entities.hand.Hand;
import model.parsers.GGPokerHandParser;
import model.parsers.GGPokerSummaryParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparingInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StaticsTest {

    Path handsFolder = Path.of("D:\\OneDrive\\Documents\\poker\\tournaments\\hand histories");
    Path summaryFolder = Path.of("D:\\OneDrive\\Documents\\poker\\tournaments\\summaries");
    Map<String, List<Hand>> handsByGameId;
    List<Game> games;
    Statics statics;
    static int n;

    @BeforeEach
    void setUp() {
        handsByGameId = new GGPokerHandParser().getGamesFromFolder(handsFolder);
        System.out.println("Hand lists size: " + handsByGameId.size());
        games = new GGPokerSummaryParser().getGamesFromFolder(summaryFolder);
        System.out.println("Games size: " + games.size());
        handsByGameId.values().forEach(hands -> {
            boolean added = addHandsToGameSummaries(hands);
            if (!added) games.add(new Game(hands));
        });
        statics = new Statics(games.stream()
                .filter(game -> game.getHands() != null)
                .flatMap(game -> game.getHands().stream()).toList());
    }

    private boolean addHandsToGameSummaries(List<Hand> hands) {
        for (Game g : games) {
            if (g.getId().equals(hands.getFirst().getId())) {
                g.setHands(hands);
                return true;
            }
        }
        return false;
    }


    @Test
    void basic() {
        Path folder = Path.of("src/test/resources/statics");
        Map<String, List<Hand>> handsByGameId = new GGPokerHandParser().getGamesFromFolder(folder);
        assertThat(handsByGameId).isNotNull().hasSize(1);
        assertThat(handsByGameId.values().stream().findFirst()).isPresent().get().extracting(List::size).isEqualTo(73);
        Statics statics = new Statics(handsByGameId.values().stream().findFirst().get());
        assertEquals(13, statics.getHoleCardsAtLeastFrom('T'));
        assertEquals(3, statics.getSuitedConnectorsCountFrom('T'));
        assertEquals(3, statics.getPairCount());
        assertEquals(2, statics.getPairCount('6'));
        assertEquals(1, statics.getPairCount('A'));
        AnalyseHand.printStat(statics);
    }

    @Test
    void mostHand() {
        games.stream()
                .sorted(comparingInt((Game o) -> o.getHands().size()).reversed())
                .filter(game -> game.getHands().size() >= 168)
                .forEach(game -> AnalyseHand.printStat(new Statics(game.getHands())));
    }

    @Test
    void getHoleCardCountStat() {
        Map<Character, Long> carsStat = statics.getHoleCardCountStat();

        carsStat.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(cardIntegerEntry -> {
                    System.out.println(cardIntegerEntry.getKey() + ": " + cardIntegerEntry.getValue());
                });
    }

    @Test
    void getCardsFrom() {
        LocalDateTime time = LocalDateTime.of(2024, 9, 3, 19, 0);
        List<Hand> list = statics.getHands().stream()
                .filter(hand -> hand.getDateTime().isAfter(time))
                .toList();
        Statics stat = new Statics(list);
        System.out.println("hands count: " + stat.getHandsCount());
        Map<Character, Long> carsStat = stat.getHoleCardCountStat();

        carsStat.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(cardIntegerEntry -> {
                    System.out.println(cardIntegerEntry.getKey() + ": " + cardIntegerEntry.getValue());
                });
    }
}