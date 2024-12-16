package bruzsal;

import java.time.LocalDate;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public record WinningNumbers6(
        int year,
        int week,
        LocalDate date,
        int count6,
        long prize6,
        int count5p1,
        int prize5p1,
        int count5,
        int prize5,
        int count4,
        int prize4,
        int count3,
        int prize3,
        List<Integer> numbers,
        int number6p1
) implements WinningNumbers {

    public static WinningNumbers6 parseSorsolas(String line) {
        line = line
                .replace("\uFEFF", "")
                .replace(" ", "")
                .replace("Ft", "")
                .replace(".", "-");
        String[] split = line.split(";");
        return new WinningNumbers6(
                parseInt(split[0]),
                parseInt(split[1]),
                split[2].isEmpty() ? null : LocalDate.parse(split[2].substring(0, split[2].length() - 1)),
                parseInt(split[3]),
                parseLong(split[4]),
                parseInt(split[5]),
                parseInt(split[6]),
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
                        parseInt(split[17]),
                        parseInt(split[18])
                ),
                split.length == 20 ? parseInt(split[19]) : 0
        );
    }

    @Override
    public int getNumberCount() {
        return 6;
    }

    @Override
    public int getMaxNumber() {
        return 45;
    }

    @Override
    public List<Integer> getNumbers() {
        return numbers;
    }
}
