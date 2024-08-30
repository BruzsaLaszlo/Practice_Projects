package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PokerParser {

    private static final Map<String, Matcher> matchers = new HashMap<>();

    public String readFromFile(File file) {
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            throw new IllegalArgumentException("File can not read: " + file, e);
        }
    }

    public double parseDouble(String input, String regex) {
        Matcher matcher = getMatcher(input, regex);
        return Double.parseDouble(matcher.group(1));
    }

    public String parseString(String input, String regex) {
        Matcher matcher = getMatcher(input, regex);
        return matcher.group(1);
    }

    public int parseInt(String input, String regex) {
        Matcher matcher = getMatcher(input, regex);
        return Integer.parseInt(matcher.group(1).replace(",", ""));
    }

    public LocalDateTime parseLocalDateTime(String input, String regex) {
        Matcher matcher = getMatcher(input, regex);
        return LocalDateTime.parse(matcher.group(1).replace("/", "-").replace(" ", "T"));
    }

    public static Matcher getMatcher(String input, String regex) {
        matchers.computeIfAbsent(regex, s -> {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher("");
        });
        Matcher matcher = matchers.get(regex).reset(input);
        matcher.find();
        return matcher;
    }

    public String getFirstLine(String input) {
        return input.substring(0, input.indexOf("\n"));
    }
}
