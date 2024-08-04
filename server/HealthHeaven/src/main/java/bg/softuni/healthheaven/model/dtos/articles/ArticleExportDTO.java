package bg.softuni.healthheaven.model.dtos.articles;

import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import bg.softuni.healthheaven.model.entities.Comment;
import bg.softuni.healthheaven.model.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleExportDTO {

    private Long id;
    private String title;
    private String content;
    private String createdDate;
    private String imageURL;
    private String author;
    private List<CommentExportDTO> comments;

}





