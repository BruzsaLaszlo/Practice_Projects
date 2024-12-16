CREATE TABLE card
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    name  CHAR         NOT NULL,
    color VARCHAR(255) NOT NULL,
    CONSTRAINT pk_card PRIMARY KEY (id)
);

CREATE TABLE game
(
    id             VARCHAR(255) NOT NULL,
    start_time     datetime     NOT NULL,
    players_count  INT          NOT NULL,
    finished_place INT          NOT NULL,
    total_prize_pool DOUBLE NOT NULL,
    winning_prize DOUBLE NOT NULL,
    buy_in DOUBLE NOT NULL,
    name           VARCHAR(255) NOT NULL,
    `description`  VARCHAR(255) NULL,
    CONSTRAINT pk_game PRIMARY KEY (id)
);

CREATE TABLE game_hands
(
    game_id  VARCHAR(255) NOT NULL,
    hands_id VARCHAR(255) NOT NULL
);

CREATE TABLE hand
(
    id            VARCHAR(255) NOT NULL,
    game_id       VARCHAR(255) NOT NULL,
    turn_id       BIGINT NULL,
    river_id      BIGINT NULL,
    level         INT          NOT NULL,
    blind         INT          NOT NULL,
    date_time     datetime     NOT NULL,
    folded_street VARCHAR(255) NULL,
    chips         INT          NOT NULL,
    chips_won     INT          NOT NULL,
    CONSTRAINT pk_hand PRIMARY KEY (id)
);

CREATE TABLE hand_flop
(
    hand_id VARCHAR(255) NOT NULL,
    flop_id BIGINT       NOT NULL
);

CREATE TABLE hand_hole
(
    hand_id VARCHAR(255) NOT NULL,
    hole_id BIGINT       NOT NULL
);

ALTER TABLE game_hands
    ADD CONSTRAINT uc_game_hands_hands UNIQUE (hands_id);

ALTER TABLE hand_flop
    ADD CONSTRAINT uc_hand_flop_flop UNIQUE (flop_id);

ALTER TABLE hand_hole
    ADD CONSTRAINT uc_hand_hole_hole UNIQUE (hole_id);

ALTER TABLE hand
    ADD CONSTRAINT FK_HAND_ON_RIVER FOREIGN KEY (river_id) REFERENCES card (id);

ALTER TABLE hand
    ADD CONSTRAINT FK_HAND_ON_TURN FOREIGN KEY (turn_id) REFERENCES card (id);

ALTER TABLE game_hands
    ADD CONSTRAINT fk_gamhan_on_game FOREIGN KEY (game_id) REFERENCES game (id);

ALTER TABLE game_hands
    ADD CONSTRAINT fk_gamhan_on_hand FOREIGN KEY (hands_id) REFERENCES hand (id);

ALTER TABLE hand_flop
    ADD CONSTRAINT fk_hand_flop_on_card FOREIGN KEY (flop_id) REFERENCES card (id);

ALTER TABLE hand_flop
    ADD CONSTRAINT fk_hand_flop_on_hand FOREIGN KEY (hand_id) REFERENCES hand (id);

ALTER TABLE hand_hole
    ADD CONSTRAINT fk_hand_hole_on_card FOREIGN KEY (hole_id) REFERENCES card (id);

ALTER TABLE hand_hole
    ADD CONSTRAINT fk_hand_hole_on_hand FOREIGN KEY (hand_id) REFERENCES hand (id);