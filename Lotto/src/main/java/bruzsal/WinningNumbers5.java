package bruzsal;

import java.time.LocalDate;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public record WinningNumbers5(
        int year,
        int week,
        LocalDate date,
        int count5,
        long prize5,
        int count4,
        int prize4,
        int count3,
        int prize3,
        int count2,
        int prize2,
        List<Integer> numbers
) {

    public static WinningNumbers5 parseSorsolas(String line) {
        line = line
                .replace("\uFEFF", "")
                .replace(" ", "")
                .replace("Ft", "")
                .replace(".", "-");
        String[] split = line.split(";");
        return new WinningNumbers5(
                parseInt(split[0]),
                parseInt(split[1]),
                split[2].isEmpty() ? null : LocalDate.parse(split[2].substring(0, split[2].length() - 1)),
                parseInt(split[3]),
                parseLong(split[4]),
                parseInt(split[7]),
                parseInt(split[8]),
                parseInt(split[9]),
                parseInt(split[10]),
                parseInt(split[11]),
                parseInt(split[12]),
                List.of(
                        parseInt(split[13]),
                        parseInt(split[14]),
                        parseInt(split[15]),
                        parseInt(split[16]),
                        parseInt(split[17])
                )
        );
    }

}
