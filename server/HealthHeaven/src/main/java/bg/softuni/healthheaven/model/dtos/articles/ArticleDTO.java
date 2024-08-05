package bg.softuni.healthheaven.model.dtos.articles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO {

    private String title;
    private String content;
    private String imageURL;
    private String author;
}
