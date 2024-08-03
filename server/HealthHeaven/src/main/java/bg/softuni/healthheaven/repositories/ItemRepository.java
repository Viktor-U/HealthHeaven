package bg.softuni.healthheaven.repositories;

import bg.softuni.healthheaven.model.entities.Item;
import bg.softuni.healthheaven.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
