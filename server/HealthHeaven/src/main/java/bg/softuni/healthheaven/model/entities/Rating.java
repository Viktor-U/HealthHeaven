package bg.softuni.healthheaven.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "reting")
@Getter
@Setter
@NoArgsConstructor
public class Rating extends BaseEntity{


    @Column(nullable = false)
    @Size(min = 1, max =5)
    private Integer rating;

    @OneToOne(fetch = FetchType.EAGER)
    private User rater;

    @ManyToOne
    private Item item;


}
