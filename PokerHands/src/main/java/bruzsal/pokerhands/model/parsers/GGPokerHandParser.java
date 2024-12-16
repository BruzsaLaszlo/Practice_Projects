package bruzsal.pokerhands.model.parsers;


import bruzsal.pokerhands.model.entities.card.Card;
import bruzsal.pokerhands.model.entities.card.CardColor;
import bruzsal.pokerhands.model.entities.card.CardRank;
import bruzsal.pokerhands.model.entities.hand.Hand;
import bruzsal.pokerhands.model.entities.hand.Streets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableMap;

public class GGPokerHandParser extends PokerParser {

    public Map<String, List<Hand>> getGamesFromFolder(Path folder) {
        try (Stream<Path> pathStream = Files.walk(folder)) {
            return pathStream
                    .filter(Files::isRegularFile)
                    .map(this::getHandsFromFile)
                    .collect(toUnmodifiableMap(
                            (List<Hand> hands) -> hands.getFirst().getGameId(),
                            hands -> hands,
                            this::mergeLists));
        } catch (IOException e) {
            throw new IllegalStateException("No files found in " + folder);
        }
    }

    private List<Hand> mergeLists(List<Hand> handList1, List<Hand> handList2) {
        return Stream.concat(handList1.stream(), handList2.stream()).toList();
    }

    public List<Hand> getHandsFromFile(Path file) {
        String gameString = readFromFile(file).trim();
        return getHandsFromFile(gameString);
    }

    private List<Hand> getHandsFromFile(String gameLines) {
        assert "www".split("\n")[0].equals("www");
        String[] split = gameLines.split("\r\n\r\n");
        if (split.length == 1) {
            split = gameLines.split("\n\n");
        }
        return stream(split)
                .map(this::createHandFrom)
                .toList();
    }

    private Hand createHandFrom(String handString) {
        Hand hand = new Hand();
        for (String line : handString.strip().split("\n")) {
            if (line.startsWith("Poker Hand")) {
                hand.setId(parseString(line, "Poker Hand #(.*): "));
                hand.setBlind(parseInt(line, "Level.*?([\\d,]*)\\)"));
                hand.setGameId(parseString(line, "Tournament #(.*?),"));
                hand.setLevel(parseInt(line, "Level(.*?)\\("));
                hand.setDateTime(parseLocalDateTime(line, "\\)\\s-\\s(.*)"));
            } else if (line.startsWith("Dealt to Hero")) {
                hand.setHole(parseCards(getSubStringBetweenLastBrackets(line)));
            } else if (line.startsWith("*** FLOP ***")) {
                hand.setFlop(parseCards(getSubStringBetweenLastBrackets(line)));
            } else if (line.startsWith("*** TURN ***")) {
                hand.setTurn(parseCard(getSubStringBetweenLastBrackets(line)));
            } else if (line.startsWith("*** RIVER ***")) {
                hand.setRiver(parseCard(getSubStringBetweenLastBrackets(line)));
            } else if (line.startsWith("Seat") && line.contains("Hero")) {
                if (line.contains("Hero folded")) hand.setFoldedStreet(parseStreet(line));
                if (line.contains("chips")) hand.setChips(parseInt(line, "\\(([\\d,]*)"));
                if (line.contains("won")) hand.setChipsWon(parseInt(line, "won \\(([\\d,]*)"));
            }
        }
        return hand;
    }

    private Streets parseStreet(String line) {
        if (line.endsWith("before Flop")) return Streets.PRE_FLOP;
        if (line.endsWith("on the Flop")) return Streets.FLOP;
        if (line.endsWith("on the Turn")) return Streets.TURN;
        if (line.endsWith("on the River")) return Streets.RIVER;
        return null;
    }

    public String getSubStringBetweenLastBrackets(String line) {
        return line.substring(line.lastIndexOf('[') + 1, line.lastIndexOf(']'));
    }


    private Card parseCard(String input) {
        char name = input.charAt(0);
        if (!CardRank.ranks.containsKey(name)) {
            throw new IllegalArgumentException("Card like this does not exist: " + name + "\n" + input);
        }
        return new Card(name, CardColor.parseFromShortName(input.charAt(1)));
    }

    private List<Card> parseCards(String input) {
        return stream(input.split(" "))
                .map(this::parseCard)
                .toList();
    }

}
