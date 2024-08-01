package bg.softuni.healthheaven.model.dtos.shop;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {

    private Long id;

    private String name;

    private String imageURL;

    private Double price;

    private String description;
}
