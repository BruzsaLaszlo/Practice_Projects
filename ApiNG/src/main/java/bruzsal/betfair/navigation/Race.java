package bruzsal.betfair.navigation;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class Race extends Node {

    private final String venue;
    private final LocalDateTime startTime;
    private final String raceNumber;
    private final String countryCode;

    private final List<Market> markets = new ArrayList<>();

    public Race(int depth, String id, String name, String venue, Date startTime, String raceNumber, String countryCode) {
        super(depth, id, name);
        this.venue = venue;
        this.startTime = new Timestamp(startTime.getTime()).toLocalDateTime();
        this.raceNumber = raceNumber;
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Race{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", venue='" + venue + '\'' +
                ", startTime=" + startTime +
                ", raceNumber='" + raceNumber + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }

}