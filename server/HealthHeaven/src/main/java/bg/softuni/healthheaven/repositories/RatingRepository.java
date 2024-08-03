package bg.softuni.healthheaven.repositories;

import bg.softuni.healthheaven.model.entities.Item;
import bg.softuni.healthheaven.model.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByRaterIdAndItemId(Long raterId, Long itemId);
}
