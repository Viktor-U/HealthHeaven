package bg.softuni.healthheaven.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends BaseEntity{

    @Column(nullable = false)
    @Size(min = 2, max =20)
    private String name;

    @Column(nullable = false)
    @Size(min = 2, max =20)
    private String specialization;


    @Column(nullable = false)
    @Size(min = 10, max =10)
    private String phoneNumber;

    @Column(columnDefinition = "text", nullable = false)
    private String profilePictureURL;

    @Column(columnDefinition = "text", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private List<Comment> comments = new ArrayList<>();

}
