package Human;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static Human.Gender.FEMALE;
import static Human.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HumanRepositoryTest {

    @Autowired
    HumanRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(new Human("Jancsi", 12, MALE));
        repository.save(new Human("Juliska", 10, FEMALE));
    }

    @AfterEach
    void tearDown() {
//        repository.deleteAll();
    }

    @Test
    void findHumanByName() {
        Optional<Human> juliska = repository.findHumanByName("Juliska");
        assertEquals("Juliska", juliska.get().getName());
        Optional<Human> juli = repository.findHumanByName("Juli");
        assertEquals(Optional.empty(), juli);
    }

    @Test
    void equalsHumanByNameAndAge() {
        Optional<Human> juliska = repository.equalsHumanByNameAndAge("Juliska", 10);
        assertEquals(10, juliska.get().getAge());
        Optional<Human> juli = repository.equalsHumanByNameAndAge("Juli", 10);
        assertEquals(Optional.empty(), juli);
    }

    @Test
    @Order(1)
    void findHumanById() {
        Optional<Human> jancsi = repository.findHumanById(1);
        assertEquals("Jancsi", jancsi.get().getName());
        Optional<Human> none = repository.findHumanById(3);
        assertThrows(NoSuchElementException.class, none::get);
    }

    @Test
    @Order(Integer.MAX_VALUE)
    void countOfHuman() {
        List<Human> all = repository.findAll();
        assertEquals(2, all.size());
    }
}