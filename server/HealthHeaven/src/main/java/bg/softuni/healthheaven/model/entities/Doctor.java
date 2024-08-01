package bg.softuni.healthheaven.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private List<Comment> comments = new ArrayList<Comment>();

}
