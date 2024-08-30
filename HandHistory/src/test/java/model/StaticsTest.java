package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparingInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StaticsTest {

    Path handsFolder = Path.of("src/test/resources/tournaments");
    Path summaryFolder = Path.of("src/test/resources/summaries");
    List<Game> gamesh;
    List<Game> games;
    Statics statics;

    @BeforeEach
    void setUp() {
        gamesh = new GGPokerHandParser().getGamesFromFolder(handsFolder);
        games = new GGPokerSummaryParser().getGamesFromFolder(summaryFolder);
        for (Game game : games) {
            gamesh.stream()
                    .filter(g -> g.getId().equals(game.getId()))
                    .findFirst().ifPresent(found -> game.setHands(found.getHands()));
        }
        statics = new Statics(games.stream()
                .filter(game -> game.getHands() != null)
                .flatMap(game -> game.getHands().stream()).toList());
    }

    @Test
    void basic() {
        Path folder = Path.of("src/test/resources/statics");
        List<Game> games = new GGPokerHandParser().getGamesFromFolder(folder);
        assertThat(games).isNotNull().hasSize(1);
        assertThat(games.getFirst().getHands()).isNotNull().hasSize(73);
        Statics statics = new Statics(games.getFirst().getHands());
        assertEquals(13, statics.getHoleCardsAtLeastRank('T'));
        assertEquals(3, statics.getSuitedConnectorsCountFrom('T'));
        assertEquals(3, statics.getPairCount());
        assertEquals(2, statics.getPairCount('6'));
        assertEquals(1, statics.getPairCount('A'));
        stat(statics);
    }

    @Test
    void mostHand() {
        games.stream()
                .sorted(comparingInt((Game o) -> o.getHands().size()).reversed())
                .filter(game -> game.getHands().size() > 50)
                .forEach(game -> stat(new Statics(game.getHands())));
    }

    @Test
    void getHoleCardCountStat() {
        Map<Card, Integer> carsStat = statics.getHoleCardCountStat();

        carsStat.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(cardIntegerEntry -> {
                    System.out.println(cardIntegerEntry.getKey() + ": " + cardIntegerEntry.getValue());
                });
    }

    void stat(Statics statics) {
        int handCount = statics.getHandsCount();
        System.out.println("Hand count: " + handCount);

        long holeCardsIsBigger9Count = statics.getHoleCardsAtLeastRank('T');
        int varianceBiggerThen9 = (int) (holeCardsIsBigger9Count - handCount * 0.143);
        System.out.println("hole cards >=T: " + holeCardsIsBigger9Count + ", v:" + varianceBiggerThen9);

        long suitedConnectors = statics.getSuitedConnectorsCountFrom('T');
        int varianceSuitedConnectors = (int) (suitedConnectors - handCount * 0.0121);
        System.out.println("suited connectors >=T " + suitedConnectors + ", v:" + varianceSuitedConnectors);

        long bigPairCount = statics.getPairCount('T', 'J', 'Q', 'K', 'A');
        int bigPairVariance = (int) (bigPairCount - handCount * 0.0226);
        System.out.println("big pair: " + bigPairCount + ", v:" + bigPairVariance);

        long pairCount = statics.getPairCount();
        int pairVariance = (int) (pairCount - handCount * 0.0588);
        System.out.println("pair count: " + pairCount + ", v:" + pairVariance);
        CardRank.ranks.keySet().forEach(name -> System.out.println(name + ": " + statics.getPairCount(name)));
    }

}