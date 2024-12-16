package bruzsal.pokerhands.controllers;

import bruzsal.pokerhands.model.dtos.HandDto;
import bruzsal.pokerhands.model.entities.hand.Hand;
import bruzsal.pokerhands.services.HandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hands")
@RequiredArgsConstructor
public class HandController {

    private final HandService service;

    @GetMapping
    public List<Hand> getHands() {
        return service.getHands();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HandDto addHand(@RequestBody Hand hand) {
        System.out.println(hand.toString());
        return service.addHand(hand);
    }

}
