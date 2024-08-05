package bg.softuni.healthheaven.model.entities;

import bg.softuni.healthheaven.model.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User extends BaseEntity{


    @Column(name = "first_name")
    @Size(max = 20)
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 20)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Size(min = 4, max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();



}