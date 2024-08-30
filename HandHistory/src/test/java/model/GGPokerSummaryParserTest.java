package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GGPokerSummaryParserTest {

    Path folder = Path.of("src/test/resources/GGPokerSummaryParserTest");
    GGPokerSummaryParser ggPokerSummaryParser = new GGPokerSummaryParser();
    List<Game> games;

    @BeforeEach
    void checkFirst() {
        games = ggPokerSummaryParser.getGamesFromFolder(folder);
        assertNotNull(games);
        assertThat(games).hasSizeGreaterThan(0);
        assertDoesNotThrow(games::getFirst);
        assertEquals("148972307", games.getFirst().getId());
        assertEquals(1.05, games.getFirst().getBuyIn());
        assertEquals(646, games.getFirst().getPlayersCount());
        assertEquals(79, games.getFirst().getFinishedPlace());
        assertEquals(2.28, games.getFirst().getWinningPrize());
        assertEquals(646, games.getFirst().getTotalPrizePool());
        assertThat(games.getFirst().getStartTime()).hasYear(2024).hasMinute(30);
        assertNull(games.getFirst().getHands());
    }

    @Test
    void getGamesFromFolder() {

    }
}