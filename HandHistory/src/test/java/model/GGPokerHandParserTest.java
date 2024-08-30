package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GGPokerHandParserTest {

    Path folder = Path.of("src/test/resources/GGPokerHandParserTest");
    GGPokerHandParser ggPokerHandParser = new GGPokerHandParser();
    List<Game> games;

    @BeforeEach
    void checkFirst() {
        games = ggPokerHandParser.getGamesFromFolder(folder);
        assertNotNull(games);
        assertDoesNotThrow(games::getFirst);
        assertEquals("160185120", games.getFirst().getId());
        assertEquals("TM3621648886", games.getFirst().getHands().getFirst().getId());
        assertEquals(6, games.getFirst().getHands().getFirst().getLevel());
        assertEquals(300, games.getFirst().getHands().getFirst().getBlind());
        assertThat(games.getFirst().getHands().getFirst().getDateTime()).hasYear(2024).hasMinute(28);
        assertFalse(games.getFirst().getHands().isEmpty());
    }

    @Test
    void parseHand() {
        List<Hand> handList = games.getFirst().getHands();

        // Last Hand Check (first in the list)
        assertEquals("TM3621648886", handList.getFirst().getId());
        assertEquals('K', handList.getFirst().getHole().getFirst().getName());
        assertEquals(CardColor.SPADES, handList.getFirst().getHole().getFirst().getColor());
        assertEquals('J', handList.getFirst().getHole().get(1).getName());
        assertEquals(CardColor.DIAMOND, handList.getFirst().getHole().get(1).getColor());
        assertEquals(3, handList.getFirst().getFlop().size());
        assertEquals('T', handList.getFirst().getFlop().getFirst().getName());
        assertEquals(CardColor.SPADES, handList.getFirst().getFlop().getFirst().getColor());
        assertEquals('K', handList.getFirst().getFlop().get(1).getName());
        assertEquals(CardColor.DIAMOND, handList.getFirst().getFlop().get(1).getColor());
        assertEquals('A', handList.getFirst().getFlop().get(2).getName());
        assertEquals(CardColor.HEART, handList.getFirst().getFlop().get(2).getColor());
        assertEquals('3', handList.getFirst().getTurn().getName());
        assertEquals(CardColor.HEART, handList.getFirst().getTurn().getColor());
        assertEquals('9', handList.getFirst().getRiver().getName());
        assertEquals(CardColor.SPADES, handList.getFirst().getRiver().getColor());

        // first hand check (last in the list)
        assertEquals("TM3621648323", handList.getLast().getId());
        assertEquals('J', handList.getLast().getHole().getFirst().getName());
        assertEquals(CardColor.DIAMOND, handList.getLast().getHole().getFirst().getColor());
        assertEquals('7', handList.getLast().getHole().get(1).getName());
        assertEquals(CardColor.HEART, handList.getLast().getHole().get(1).getColor());
        assertEquals('8', handList.getLast().getFlop().getFirst().getName());
        assertEquals(CardColor.DIAMOND, handList.getLast().getFlop().getFirst().getColor());
        assertEquals('3', handList.getLast().getFlop().get(1).getName());
        assertEquals(CardColor.DIAMOND, handList.getLast().getFlop().get(1).getColor());
        assertEquals('A', handList.getLast().getFlop().get(2).getName());
        assertEquals(CardColor.DIAMOND, handList.getLast().getFlop().get(2).getColor());
        assertNull(handList.getLast().getTurn());
        assertNull(handList.getLast().getRiver());
    }

    @Test
    void name() throws IOException {
        Path file = Path.of("src/test/resources/GGPokerHandParserTest/test.dat");
        String s = Files.readString(file);
        System.out.println(s.length());
        String[] split = s.split("\r\n\r\n");
        System.out.println(split.length);
        char property = System.lineSeparator().charAt(0);
        System.out.println((int) property);
        char cr = '\r';
        System.out.println((int) cr);
        char lf = '\n';
        System.out.println((int) lf);
    }
}