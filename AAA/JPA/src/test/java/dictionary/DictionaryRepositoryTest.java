package dictionary;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class DictionaryRepositoryTest {

    DictionaryRepository repository;

    @BeforeEach
    void setUp() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        repository = new DictionaryRepository(factory);
    }

    @SneakyThrows
    private List<EnglishHungarian> getTranslatesFromFile() {
        var hungarian = Files.readAllLines(Path.of("src/main/resources/Magyar.txt"));
        var english = Files.readAllLines(Path.of("src/main/resources/Angol.txt"));

        List<EnglishHungarian> result = new ArrayList<>();
        for (int i = 0; i < hungarian.size(); i++) {
            result.add(new EnglishHungarian((long) i + 1, english.get(i), hungarian.get(i)));
        }
        return result;
    }

    @Test
    void saveAll() {
        repository.saveAll(getTranslatesFromFile());
    }

    @Test
    void loadAll() {
        var actual = repository.loadAll();
        assertFalse(actual.isEmpty());
        String[] s = new String[1];
        actual.forEach(System.out::println);
    }
}