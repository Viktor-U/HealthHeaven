package bg.softuni.healthheaven.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
public class Item extends BaseEntity{

    @Column(nullable = false)
    private String name;

    private Double price;

    @Column(nullable = false)
    private String description;
}
