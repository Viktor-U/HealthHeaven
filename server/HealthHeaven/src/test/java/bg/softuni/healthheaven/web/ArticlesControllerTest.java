package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.articles.ArticleDTO;
import bg.softuni.healthheaven.model.dtos.articles.ArticleExportDTO;
import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import bg.softuni.healthheaven.services.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ArticlesControllerTest {

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticlesController articlesController;

    private ArticleExportDTO article1;
    private ArticleExportDTO article2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        List<CommentExportDTO> comments = Collections.emptyList();
        article1 = ArticleExportDTO.builder()
                .id(1L)
                .title("Title 1")
                .content("Content 1")
                .timeOnPost("2024-08-07T12:34:56")
                .imageURL("http://example.com/image1.jpg")
                .author("Author 1")
                .comments(comments)
                .build();

        article2 = ArticleExportDTO.builder()
                .id(2L)
                .title("Title 2")
                .content("Content 2")
                .timeOnPost("2024-08-07T12:34:56")
                .imageURL("http://example.com/image2.jpg")
                .author("Author 2")
                .comments(comments)
                .build();
    }

    @Test
    public void testGetAllArticles() {
        List<ArticleExportDTO> articles = Arrays.asList(article1, article2);
        when(articleService.getAllArticles()).thenReturn(articles);

        ResponseEntity<List<ArticleExportDTO>> response = articlesController.getAllArticles();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals(article1, response.getBody().get(0));
        assertEquals(article2, response.getBody().get(1));
    }

    @Test
    public void testGetOneArticle() {
        when(articleService.getOneArticle(1L)).thenReturn(article1);

        ResponseEntity<ArticleExportDTO> response = articlesController.getOneArticle(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(article1, response.getBody());
    }

    @Test
    public void testCreateArticle() {
        ArticleDTO articleDTO = new ArticleDTO("Title 1", "Content 1", "http://example.com/image1.jpg", "Author 1");
        when(articleService.createArticle(any(ArticleDTO.class))).thenReturn(article1);

        ResponseEntity<ArticleExportDTO> response = articlesController.createArticle(articleDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(article1, response.getBody());
    }

    @Test
    public void testDeleteArticle() {
        doNothing().when(articleService).deleteArticle(1L);

        articlesController.deleteArticle(1L);

        verify(articleService, times(1)).deleteArticle(1L);
    }
}
