package bg.softuni.healthheaven.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends BaseEntity{

    private String name;

    private String specialization;

    private String phoneNumber;

    private String email;

    private String address;

    private String description;

    private String profilePictureURL;
}
