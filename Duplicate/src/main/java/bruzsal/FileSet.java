package bruzsal;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@Getter
public class FileSet {

    private final Set<File> set;

    public FileSet(Path directory) {
        try (Stream<Path> walk = Files.walk(directory)) {
            set = walk.filter(Files::isRegularFile)
                    .map(File::new)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
