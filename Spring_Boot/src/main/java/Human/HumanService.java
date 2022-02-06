package Human;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static Human.Gender.FEMALE;
import static Human.Gender.MALE;

@Service
public class HumanService {

    private final HumanRepository humanRepository;

    @Autowired
    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    public List<Human> getHumans() {
        return humanRepository.findAll();
    }

    public String addAllHumans() {
        List<Human> humans = List.of(
                new Human("Pistike", 8, MALE),
                new Human("dr. Kovács István", 55, FEMALE));
        humans.forEach(human -> humanRepository.findHumanByName(human.getName())
                .ifPresent(humanOptinal -> {
                    throw new IllegalStateException("van már ilyen név: " + humanOptinal.getName());
                }));
        humanRepository.saveAll(humans);
        return humans.toString();
    }

    public HumanRepository getHumanRepository() {
        return humanRepository;
    }

    public void deleteHuman(long id) {
        humanRepository.findHumanById(id)
                .ifPresentOrElse(
                        humanRepository::delete,
                        () -> {
                            throw new IllegalArgumentException("nincs ilyen id, nem lehet törölni!");
                        });
    }

    @Transactional
    public Human updateHuman(long humanId, String name, Integer age) {
        Human human = humanRepository.findHumanById(humanId).orElseThrow();
        human.setName(human.getName() + "Plus100");
        human.setAge(human.getAge() + 100);
        return human;
    }

    public void addHuman(Human human) {
        humanRepository.equalsHumanByNameAndAge(human.getName(), human.getAge())
                .ifPresent(h -> {
                    throw new IllegalStateException("Van már ilyen nevü és korú!");
                });
        humanRepository.save(human);
    }
}
