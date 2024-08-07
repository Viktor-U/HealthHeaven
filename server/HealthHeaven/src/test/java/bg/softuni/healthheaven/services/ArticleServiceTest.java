package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.articles.ArticleDTO;
import bg.softuni.healthheaven.model.dtos.articles.ArticleExportDTO;
import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import bg.softuni.healthheaven.model.entities.Article;
import bg.softuni.healthheaven.model.entities.Comment;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.repositories.ArticleRepository;
import bg.softuni.healthheaven.repositories.CommentRepository;
import bg.softuni.healthheaven.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ArticleService articleService;

    private Article article;
    private ArticleDTO articleDTO;
    private ArticleExportDTO articleExportDTO;
    private User user;
    private Comment comment;
    private CommentExportDTO commentExportDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");

        comment = new Comment();
        comment.setId(1L);
        comment.setContent("Sample comment");
        comment.setAuthor(user);

        article = new Article();
        article.setId(1L);
        article.setTitle("Sample Article");
        article.setContent("This is a sample article.");
        article.setTimeOnPost(Instant.now());
        article.setImageURL("http://example.com/image.jpg");
        article.setAuthor(user);
        article.setComments(Collections.singletonList(comment));

        articleDTO = new ArticleDTO();
        articleDTO.setTitle("Sample Article");
        articleDTO.setContent("This is a sample article.");
        articleDTO.setImageURL("http://example.com/image.jpg");
        articleDTO.setAuthor("johndoe@example.com");

        commentExportDTO = new CommentExportDTO();
        commentExportDTO.setId(1L);
        commentExportDTO.setContent("Sample comment");
        commentExportDTO.setAuthor("John Doe");
        commentExportDTO.setTimeOnPost(Instant.now().toString());

        articleExportDTO = new ArticleExportDTO();
        articleExportDTO.setId(1L);
        articleExportDTO.setTitle("Sample Article");
        articleExportDTO.setContent("This is a sample article.");
        articleExportDTO.setTimeOnPost(Instant.now().toString());
        articleExportDTO.setImageURL("http://example.com/image.jpg");
        articleExportDTO.setAuthor("John Doe");
        articleExportDTO.setComments(Collections.singletonList(commentExportDTO));
    }

    @Test
    public void testGetAllArticles() {
        when(articleRepository.findAll()).thenReturn(Collections.singletonList(article));
        when(modelMapper.map(any(Article.class), eq(ArticleExportDTO.class))).thenReturn(articleExportDTO);

        List<ArticleExportDTO> result = articleService.getAllArticles();

        assertEquals(1, result.size());
        assertEquals(articleExportDTO, result.get(0));
    }

    @Test
    public void testGetOneArticle() {
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(modelMapper.map(any(Article.class), eq(ArticleExportDTO.class))).thenReturn(articleExportDTO);

        ArticleExportDTO result = articleService.getOneArticle(1L);

        assertEquals(articleExportDTO, result);
    }

    @Test
    public void testCreateArticle() {
        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.of(user));
        when(articleRepository.save(any(Article.class))).thenReturn(article);
        when(modelMapper.map(any(ArticleDTO.class), eq(Article.class))).thenReturn(article);
        when(modelMapper.map(any(Article.class), eq(ArticleExportDTO.class))).thenReturn(articleExportDTO);

        ArticleExportDTO result = articleService.createArticle(articleDTO);

        assertEquals(articleExportDTO, result);
    }

    @Test
    public void testDeleteArticle() {
        doNothing().when(articleRepository).deleteById(1L);

        articleService.deleteArticle(1L);

        verify(articleRepository, times(1)).deleteById(1L);
    }
}
