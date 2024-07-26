package bg.softuni.healthheaven.model.entities;

import jakarta.persistence.Column;
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

    @Column(columnDefinition = "text")
    private String profilePictureURL;

    @Column(columnDefinition = "text")
    private String description;

}
