package Human;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static Human.Gender.FEMALE;
import static Human.Gender.MALE;

@Configuration
public class HumanConfig {

    @Bean
    CommandLineRunner commandLineRunner(HumanRepository humanRepository) {
        return args -> {
            List<Human> humans = List.of(
                    new Human("Jancsi", 36, MALE),
                    new Human("Juliska", 75, FEMALE));
            humanRepository.saveAll(humans);
        };
    }

}
