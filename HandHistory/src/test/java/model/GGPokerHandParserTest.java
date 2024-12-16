package model;

import model.entities.card.CardColor;
import model.entities.hand.Hand;
import model.parsers.GGPokerHandParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GGPokerHandParserTest {

    Path folder = Path.of("src/test/resources/GGPokerHandParserTest");
    GGPokerHandParser ggPokerHandParser = new GGPokerHandParser();
    Map<String, List<Hand>> handsByGameId;
    List<Hand> hands;

    @BeforeEach
    void checkFirst() {
        handsByGameId = ggPokerHandParser.getGamesFromFolder(folder);
        assertNotNull(handsByGameId);
        assertFalse(handsByGameId.isEmpty());
        assertThat(handsByGameId.values().stream().findFirst()).isNotEmpty();
        hands = handsByGameId.values().stream().findFirst().get();
        assertEquals("160185120", hands.getFirst().getGameId());
        assertEquals("TM3621648886", hands.getFirst().getId());
        assertEquals(6, hands.getFirst().getLevel());
        assertEquals(300, hands.getFirst().getBlind());
        assertThat(hands.getFirst().getDateTime()).hasYear(2024).hasMinute(28);
        assertFalse(hands.isEmpty());
    }

    @Test
    void parseHand() {

        // Last Hand Check (first in the list)
        assertEquals("TM3621648886", hands.getFirst().getId());
        assertEquals('K', hands.getFirst().getHole().getFirst().getName());
        assertEquals(CardColor.SPADES, hands.getFirst().getHole().getFirst().getColor());
        assertEquals('J', hands.getFirst().getHole().get(1).getName());
        assertEquals(CardColor.DIAMOND, hands.getFirst().getHole().get(1).getColor());
        assertEquals(3, hands.getFirst().getFlop().size());
        assertEquals('T', hands.getFirst().getFlop().getFirst().getName());
        assertEquals(CardColor.SPADES, hands.getFirst().getFlop().getFirst().getColor());
        assertEquals('K', hands.getFirst().getFlop().get(1).getName());
        assertEquals(CardColor.DIAMOND, hands.getFirst().getFlop().get(1).getColor());
        assertEquals('A', hands.getFirst().getFlop().get(2).getName());
        assertEquals(CardColor.HEART, hands.getFirst().getFlop().get(2).getColor());
        assertEquals('3', hands.getFirst().getTurn().getName());
        assertEquals(CardColor.HEART, hands.getFirst().getTurn().getColor());
        assertEquals('9', hands.getFirst().getRiver().getName());
        assertEquals(CardColor.SPADES, hands.getFirst().getRiver().getColor());

        // first hand check (last in the list)
        assertEquals("TM3621648323", hands.getLast().getId());
        assertEquals('J', hands.getLast().getHole().getFirst().getName());
        assertEquals(CardColor.DIAMOND, hands.getLast().getHole().getFirst().getColor());
        assertEquals('7', hands.getLast().getHole().get(1).getName());
        assertEquals(CardColor.HEART, hands.getLast().getHole().get(1).getColor());
        assertEquals('8', hands.getLast().getFlop().getFirst().getName());
        assertEquals(CardColor.DIAMOND, hands.getLast().getFlop().getFirst().getColor());
        assertEquals('3', hands.getLast().getFlop().get(1).getName());
        assertEquals(CardColor.DIAMOND, hands.getLast().getFlop().get(1).getColor());
        assertEquals('A', hands.getLast().getFlop().get(2).getName());
        assertEquals(CardColor.DIAMOND, hands.getLast().getFlop().get(2).getColor());
        assertNull(hands.getLast().getTurn());
        assertNull(hands.getLast().getRiver());
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