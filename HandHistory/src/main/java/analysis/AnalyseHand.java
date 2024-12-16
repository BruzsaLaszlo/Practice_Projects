package analysis;

import model.entities.card.CardRank;
import model.entities.hand.Hand;
import model.parsers.GGPokerHandParser;

import java.nio.file.Path;
import java.util.List;

public class AnalyseHand {

    public static void main(String[] args) {
        Path folder = Path.of("C:\\temp\\poker\\new\\GG20240909-0738 - Bounty Hunters Special 1.08.txt");
        List<Hand> hands = new GGPokerHandParser().getHandsFromFile(folder);
        Statics statics = new Statics(hands);
        printStat(statics);
    }

    public static void printStat(Statics statics) {
        int handCount = statics.getHandsCount();
        System.out.println("Hand count: " + handCount);

        long holeCardsIsBigger9Count = statics.getHoleCardsAtLeastFrom('T');
        int varianceBiggerThen9 = (int) (holeCardsIsBigger9Count - handCount * 0.143);
        System.out.println("hole cards >=T: " + holeCardsIsBigger9Count + ", v:" + varianceBiggerThen9);

        long connectors = statics.getConnectorsCountFrom('2');
        int varianceConnectors = (int) (connectors - handCount * 0.157);
        System.out.println("conectors " + connectors + ", v:" + varianceConnectors);

        long suitedConnectors = statics.getSuitedConnectorsCountFrom('T');
        int varianceSuitedConnectors = (int) (suitedConnectors - handCount * 0.0121);
        System.out.println("suited con >=T " + suitedConnectors + ", v:" + varianceSuitedConnectors);

        long bigPairCount = statics.getPairCount('T', 'J', 'Q', 'K', 'A');
        int bigPairVariance = (int) (bigPairCount - handCount * 0.0226);
        System.out.println("big pair >=T: " + bigPairCount + ", v:" + bigPairVariance);

        long pairCount = statics.getPairCount();
        int pairVariance = (int) (pairCount - handCount * 0.0588);
        System.out.println("pair count: " + pairCount + ", v:" + pairVariance);
        CardRank.ranks.keySet().forEach(name -> System.out.println(name + ": " + statics.getPairCount(name)));
    }

}
