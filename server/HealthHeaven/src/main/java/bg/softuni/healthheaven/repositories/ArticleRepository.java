package bg.softuni.healthheaven.repositories;


import bg.softuni.healthheaven.model.entities.Article;

import bg.softuni.healthheaven.model.entities.Comment;
import bg.softuni.healthheaven.model.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
