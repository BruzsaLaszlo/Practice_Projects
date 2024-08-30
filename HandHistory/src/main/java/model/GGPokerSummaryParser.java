package model;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class GGPokerSummaryParser extends PokerParser {

    public List<Game> getGamesFromFolder(Path folder) {
        List<Game> games = new ArrayList<>();
        File dir = new File(folder.toUri());
        for (File gameFile : dir.listFiles()) {
            Game game = getGameFromFile(gameFile);
            games.add(game);
        }
        return games;
    }

    private Game getGameFromFile(File gameFile) {
        String gameString = readFromFile(gameFile);

        Game game = new Game(parseString(gameString, "Tournament #(\\d*),"));
        game.setName(getFirstLine(gameString));
        game.setBuyIn(parseBuyIn(parseString(gameString, "Buy-in: (.*)")));
        game.setPlayersCount(parseInt(gameString, "(\\d*) Players"));
        game.setTotalPrizePool(parseDouble(gameString, "Total Prize Pool: \\$(\\d*)"));
        game.setStartTime(parseLocalDateTime(gameString, "Tournament started (.*\\d)"));
        game.setFinishedPlace(parseInt(gameString, "(\\d*)\\w* place"));
        game.setWinningPrize(parseDouble(gameString, "Hero, \\$?(\\d*\\.?\\d*)"));
        game.setDescription(gameString.substring(0, gameString.length() - 3));
        return game;
    }

    private double parseBuyIn(String line) {
        Matcher matcher = getMatcher(line, "\\$(\\d*\\.?\\d*)");
        double sum = 0;
        do {
            sum += Double.parseDouble(matcher.group().replace("$", ""));
        } while (matcher.find());
        return sum;
    }


}
