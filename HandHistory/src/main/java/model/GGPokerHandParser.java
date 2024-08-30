package model;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GGPokerHandParser extends PokerParser {

    public List<Game> getGamesFromFolder(Path folder) {
        List<Game> games = new ArrayList<>();
        File dir = new File(folder.toUri());
        for (File gameFile : dir.listFiles()) {
            String gameString = readFromFile(gameFile).trim();
            List<Hand> hands = getHandsFromFile(gameString);
            games.add(new Game(hands));
        }
        return games;
    }

    private List<Hand> getHandsFromFile(String gameLines) {
        List<Hand> hands = new ArrayList<>();
        String[] split = gameLines.split("\r\n\r\n");
        if (split.length == 1) {
            split = gameLines.split("\n\n");
        }
        for (String handString : split) {
            Hand hand = createHandFrom(handString);
            hands.add(hand);
        }
        return hands;
    }

    private Hand createHandFrom(String handString) {
        Hand hand = new Hand();
        for (String line : handString.strip().split("\n")) {
            if (line.startsWith("Poker Hand")) {
                hand.setId(parseString(handString, "Poker Hand #(.*): "));
                hand.setBlind(parseInt(handString, "Level.*?([\\d,]*)\\)"));
                hand.setGameId(parseString(handString, "Tournament #(.*?),"));
                hand.setLevel(parseInt(handString, "Level(.*?)\\("));
                hand.setDateTime(parseLocalDateTime(handString, "\\)\\s-\\s(.*)"));
            } else if (line.startsWith("Dealt to Hero")) {
                hand.setHole(parseCards(getSubStringBetweenLastBrackets(line)));
            } else if (line.startsWith("*** FLOP ***")) {
                hand.setFlop(parseCards(getSubStringBetweenLastBrackets(line)));
            } else if (line.startsWith("*** TURN ***")) {
                hand.setTurn(parseCard(getSubStringBetweenLastBrackets(line)));
            } else if (line.startsWith("*** RIVER ***")) {
                hand.setRiver(parseCard(getSubStringBetweenLastBrackets(line)));
            }
        }
        return hand;
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
        List<Card> cards = new ArrayList<>();
        for (String s : input.split(" ")) {
            cards.add(parseCard(s));
        }
        return cards;
    }

}
