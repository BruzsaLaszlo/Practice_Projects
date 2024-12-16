package bruzsal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Lottery {

    private final List<WinningNumbers> winningNumbers = new ArrayList<>();

    public void readFromFile(Path path) {
        try {
            winningNumbers.clear();
            Files.readAllLines(path).forEach(line -> winningNumbers.add(WinningNumbers6.parseSorsolas(line)));
        } catch (IOException e) {
            throw new IllegalArgumentException("Can not read file: " + path);
        }
    }

    public void refreshFromInternet(String url) {
        Path path = Path.of("lotto.csv");
        try (HttpClient httpClient = HttpClient.newHttpClient();
             FileOutputStream fos = new FileOutputStream(path.toString())
        ) {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            fos.write(response.body().readAllBytes());
            fos.flush();
            response.body().close();

            readFromFile(path);
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<WinningNumbers> getSorsolasok(int game) {
        return winningNumbers.stream()
                .filter(draw -> draw.getNumberCount() == game)
                .toList();
    }

    public Map<Integer, Integer> getNumberStat() {
        if (winningNumbers.isEmpty()) throw new IllegalArgumentException("List can not be empty!");
        Map<Integer, Integer> numberCount = new HashMap<>();

        winningNumbers.forEach(sorsolas -> sorsolas.getNumbers().forEach(number -> {
            if (number == 0) return;
            if (numberCount.containsKey(number)) numberCount.put(number, numberCount.get(number) + 1);
            else numberCount.put(number, 1);
        }));

        return numberCount;
    }

    public boolean isNumberPulledOut(List<Integer> nums) {
        return winningNumbers.stream()
                .anyMatch(sorsolas -> sorsolas.getNumbers().equals(nums));
    }

    public List<WinningNumbers> isNumberPartiallyPulledOut(List<Integer> nums, int matchedCount) {
        int numberCount = winningNumbers.getFirst().getNumberCount();
        return winningNumbers.stream()
                .filter(sorsolas -> {
                    Set<Integer> set = new HashSet<>(nums);
                    set.addAll(sorsolas.getNumbers());
                    return set.size() == numberCount * 2 - matchedCount;
                })
                .toList();
    }

    public boolean isSamePulledOut() {
        for (int i = 0; i < winningNumbers.size() - 1; i++) {
            for (int j = i + 1; j < winningNumbers.size(); j++) {
                if (winningNumbers.get(i).getNumbers().equals(winningNumbers.get(j).getNumbers())) return true;
            }
        }
        return false;
    }
}
