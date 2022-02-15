package dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EnglishHungarian {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "english", length = 50)
    private String english;

    @Column(name = "hungarian", length = 56)
    private String hungarian;

}
