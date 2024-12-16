package bruzsal.pokerhands.model.dtos;

import bruzsal.pokerhands.model.entities.card.CardColor;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link bruzsal.pokerhands.model.entities.card.Card}
 */
@Data
public class CardDto implements Serializable {
    String id;
    char name;
    CardColor color;
}