package bg.softuni.healthheaven.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
public class Item extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(name = "image-url", columnDefinition = "text")
    private String imageURL;

    @Column(nullable = false, columnDefinition =  "text")
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Rating> ratings = new ArrayList<>();
}
