package bruzsal.pokerhands.controllers;

import bruzsal.pokerhands.model.dtos.HandDto;
import bruzsal.pokerhands.model.entities.card.Card;
import bruzsal.pokerhands.model.entities.hand.Hand;
import bruzsal.pokerhands.model.entities.hand.Streets;
import bruzsal.pokerhands.repositories.CardRepository;
import bruzsal.pokerhands.repositories.HandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static bruzsal.pokerhands.model.entities.card.CardColor.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(statements = {"DELETE FROM hand", "DELETE FROM card"})
class HandControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    HandRepository handRepository;
    @Autowired
    CardRepository cardRepository;

    Hand save;
    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        save = handRepository.save(new Hand(
                "id" + new Random().nextInt(),
                "gameId",
                List.of(new Card('A', CLUB), new Card('K', CLUB)),
                List.of(new Card('Q', CLUB), new Card('J', CLUB), new Card('T', CLUB)),
                new Card('A', DIAMOND),
                new Card('A', HEART),
                1,
                1_000,
                LocalDateTime.of(2024, 9, 5, 12, 17),
                Streets.RIVER,
                100_000,
                20_000
        ));
    }

    @Test
    void getHands() {
        List<HandDto> responseBody = webTestClient
                .get()
                .uri("/api/hands")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(HandDto.class)
                .returnResult()
                .getResponseBody();
        assertThat(responseBody)
                .hasSizeGreaterThan(0)
                .contains(modelMapper.map(save, HandDto.class));
    }

    @Test
    void addHand() {
    }
}