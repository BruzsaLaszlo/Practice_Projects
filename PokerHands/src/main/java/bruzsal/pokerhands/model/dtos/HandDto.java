package bruzsal.pokerhands.model.dtos;

import bruzsal.pokerhands.model.entities.card.CardColor;
import bruzsal.pokerhands.model.entities.hand.Hand;
import bruzsal.pokerhands.model.entities.hand.Streets;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Hand}
 */
@Data
public class HandDto implements Serializable {

    String id;
    String gameId;

    List<CardDto> hole;
    List<CardDto> flop;
    String turnId;
    char turnName;
    CardColor turnColor;
    CardDto river;
    int level;
    int blind;
    LocalDateTime dateTime;
    Streets foldedStreet;
    int chips;
    int chipsWon;


}