package bruzsal.pokerhands.repositories;

import bruzsal.pokerhands.model.entities.hand.Hand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandRepository extends JpaRepository<Hand, String> {

}
