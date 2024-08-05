package bg.softuni.healthheaven.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;


@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
public class Article extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String imageURL;

    @Column(nullable = false)
    private String content;

    private Instant timeOnPost;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
}