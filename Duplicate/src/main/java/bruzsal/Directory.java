package bruzsal;

import lombok.Getter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
public class Directory {

    private final Path path;

    public Directory(String dir) {
        this(Paths.get(dir));
    }

    public Directory(Path dir) {
        if (!Files.exists(dir)) {
            throw new IllegalArgumentException("Directory doesn't exists: " + dir);
        }
        path = dir;
    }

}
