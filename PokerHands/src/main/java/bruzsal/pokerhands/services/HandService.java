package bruzsal.pokerhands.services;

import bruzsal.pokerhands.model.dtos.HandDto;
import bruzsal.pokerhands.model.entities.hand.Hand;
import bruzsal.pokerhands.repositories.HandRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HandService {

    private final HandRepository repository;
    private final ModelMapper modelMapper;

    public List<Hand> getHands() {
        return repository.findAll();
    }


    public HandDto addHand(Hand hand) {
        return modelMapper.map(repository.save(hand), HandDto.class);
    }
}
