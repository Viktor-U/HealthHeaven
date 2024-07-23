package bg.softuni.healthheaven.repositories;

import bg.softuni.healthheaven.model.entities.UserRole;
import bg.softuni.healthheaven.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{


    UserRole findByRole(RoleEnum roleEnum);
}
