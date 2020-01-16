package gymapp.gymapp.Repositories;

import gymapp.gymapp.Models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}
