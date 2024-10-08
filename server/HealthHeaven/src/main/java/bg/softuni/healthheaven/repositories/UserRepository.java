package bg.softuni.healthheaven.repositories;

import bg.softuni.healthheaven.model.entities.Item;
import bg.softuni.healthheaven.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);



}
