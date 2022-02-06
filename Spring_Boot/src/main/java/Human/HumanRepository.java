package Human;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HumanRepository extends JpaRepository<Human, Long> {

    @Query("SELECT h FROM Human h WHERE h.name = ?1")
    Optional<Human> findHumanByName(String name);

    @Query("SELECT h FROM Human h WHERE h.name = ?1 and h.age = ?2")
    Optional<Human> equalsHumanByNameAndAge(String name, int age);

    @Query("SELECT h from Human h WHERE h.id = ?1")
    Optional<Human> findHumanById(long id);

}
