package bg.softuni.healthheaven.model.dtos.commet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {

    private String author;
    private String content;
    private String timeOnPost;
}
