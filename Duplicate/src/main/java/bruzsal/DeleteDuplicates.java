package bruzsal;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;


@Log4j2
public class DeleteDuplicates {

    public void deleteInSecond(Path mainDir, Path secondDir) {
        FileSet filesMain = new FileSet(mainDir);

        try (Stream<Path> walk = Files.walk(secondDir)) {
            List<File> listOfDeleted = walk.filter(Files::isRegularFile)
                    .map(File::new)
                    .filter(file -> filesMain.getSet().contains(file))
                    .toList();

            listOfDeleted.forEach(file -> {
                try {
                    Files.delete(file.getFilePath());
                } catch (IOException e) {
                    throw new IllegalStateException("Can not delete file: " + file.getFilePath(), e);
                }
            });
            List<String> fileNames = listOfDeleted.stream()
                    .map(file -> file.getFilePath().toString())
                    .peek(s -> log.info("File: {}", s))
                    .toList();
            Path deletedFiles = Path.of("deletedFiles.txt");
            Files.write(deletedFiles, fileNames);
            log.info("Files: {}\nCount of files: {}", deletedFiles.toString(), listOfDeleted.size());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

}