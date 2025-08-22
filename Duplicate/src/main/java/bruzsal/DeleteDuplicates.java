package bruzsal;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Log4j2
public class DeleteDuplicates {

    public void deleteInSecond(Path mainDir, Path secondDir) {
        FileSet filesMain = new FileSet(mainDir);

        try (Stream<Path> walk = Files.walk(secondDir)) {
            walk.filter(Files::isRegularFile)
                    .map(File::new)
                    .filter(file -> filesMain.getSet().contains(file))
                    .collect(Collectors.toSet())
                    .forEach(file -> {
                        try {
                            Files.delete(file.getFilePath());
                        } catch (IOException e) {
                            throw new IllegalStateException("Can not delete file: " + file.getFilePath(), e);
                        }
                    });
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

}