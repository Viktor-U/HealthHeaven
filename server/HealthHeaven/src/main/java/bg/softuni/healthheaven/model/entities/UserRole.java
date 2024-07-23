package bg.softuni.healthheaven.model.entities;

import bg.softuni.healthheaven.model.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class UserRole extends BaseEntity{



    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
