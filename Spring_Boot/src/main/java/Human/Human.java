package Human;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Human {

    @Id
    @SequenceGenerator(name = "human_sequence", sequenceName = "human_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "human_sequence")
    private Long id;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

//    @ElementCollection(targetClass = Skills.class)
//    private List<Skills> skills = List.of(
//            new Skills(true, false, "szar")
//    );

    @Transient
    private String email;

    @Transient
    private LocalDate dateOfBirth;


    @Entity
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Skills {
        @Id
        long humanId;
        boolean smart;
        boolean clever;
        String other;
    }

    public Human(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        email = name + age + "@gmail.com";
        dateOfBirth = LocalDate.now().minusYears(age);
    }


}
