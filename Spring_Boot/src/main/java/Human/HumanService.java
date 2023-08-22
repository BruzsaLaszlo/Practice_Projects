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

    @Transactional
    public String addAllHumans() {
        List<Human> humans = List.of(
                new Human("Pistike", 8, MALE),
                new Human("dr. Kovács István", 55, FEMALE));
        humans = humans.stream()
                .filter(human -> humanRepository.findHumanByName(human.getName()).isEmpty())
                .toList();
        humanRepository.saveAll(humans);
        return humans.toString();
    }

    public void deleteHuman(long id) {
        Human human = humanRepository.findHumanById(id)
                .orElseThrow(() -> new IllegalArgumentException("nincs ilyen id, nem lehet törölni!"));
        humanRepository.delete(human);
    }

    @Transactional
    public Human updateHuman(long humanId, String name, Integer age) {
        Human human = humanRepository.findHumanById(humanId).orElseThrow();
        human.setName(human.getName() + "Plus100");
        human.setAge(human.getAge() + 100);
        return human;
    }

    public void addHuman(Human human) {
        if (humanRepository.equalsHumanByNameAndAge(human.getName(), human.getAge()).isPresent()) {
            throw new IllegalStateException("Van már ilyen nevü és korú! " + human);
        }
        humanRepository.save(human);
    }
}
