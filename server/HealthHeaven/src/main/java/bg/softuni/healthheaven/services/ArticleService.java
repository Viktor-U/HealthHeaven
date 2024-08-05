package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.articles.ArticleDTO;
import bg.softuni.healthheaven.model.dtos.articles.ArticleExportDTO;
import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import bg.softuni.healthheaven.model.entities.Article;
import bg.softuni.healthheaven.repositories.ArticleRepository;
import bg.softuni.healthheaven.repositories.CommentRepository;
import bg.softuni.healthheaven.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public List<ArticleExportDTO> getAllArticles() {

        List<Article> all = articleRepository.findAll();

        List<ArticleExportDTO> result = new ArrayList<>();

        for (Article e : all) {
            ArticleExportDTO map = modelMapper.map(e, ArticleExportDTO.class);
           map.setAuthor(e.getAuthor().getFirstName() + " " + e.getAuthor().getLastName());
            result.add(map);
        }

        return result;
    }

    public ArticleExportDTO getOneArticle(Long id) {
        Article article = articleRepository.findById(id).get();
        ArticleExportDTO result = modelMapper.map(article, ArticleExportDTO.class);
        result.setAuthor(article.getAuthor().getFirstName() + " " + article.getAuthor().getLastName());

        if (!result.getComments().isEmpty()) {
            for (CommentExportDTO comment : result.getComments()) {
                comment.setAuthor(commentRepository.findById(comment.getId()).get().getAuthor().getFirstName()
                        + " " + commentRepository.findById(comment.getId()).get().getAuthor().getLastName());
            }
        }
        return result;
    }

    public ArticleExportDTO createArticle(ArticleDTO articleDTO) {

        Article article = modelMapper.map(articleDTO, Article.class);
        article.setTimeOnPost(Instant.now());
        article.setAuthor(userRepository.findByEmail(articleDTO.getAuthor()).get());
        articleRepository.save(article);

        ArticleExportDTO result = modelMapper.map(article, ArticleExportDTO.class);
        result.setAuthor(userRepository.findByEmail(articleDTO.getAuthor()).get().getEmail());
        return result;

    }
}

