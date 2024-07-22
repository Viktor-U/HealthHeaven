package bg.softuni.healthheaven.repositories;

import bg.softuni.healthheaven.model.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
