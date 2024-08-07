package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.commet.CommentDTO;
import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import bg.softuni.healthheaven.services.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private CommentDTO commentDTO;
    private CommentExportDTO commentExportDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        commentDTO = new CommentDTO();
        commentDTO.setAuthor("johndoe@example.com");
        commentDTO.setContent("Sample comment");

        commentExportDTO = new CommentExportDTO();
        commentExportDTO.setId(1L);
        commentExportDTO.setAuthor("John Doe");
        commentExportDTO.setContent("Sample comment");
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    public void testPostCommentDoctor() {
        when(commentService.addCommentDoctor(any(CommentDTO.class), eq(1L))).thenReturn(commentExportDTO);

        ResponseEntity<CommentExportDTO> response = commentController.postCommentDoctor(commentDTO, 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(commentExportDTO, response.getBody());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    public void testPostCommentArticle() {
        when(commentService.addCommentArticle(any(CommentDTO.class), eq(1L))).thenReturn(commentExportDTO);

        ResponseEntity<CommentExportDTO> response = commentController.postCommentArticle(commentDTO, 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(commentExportDTO, response.getBody());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    public void testDeleteCommentDoctor() {
        List<CommentExportDTO> comments = Collections.singletonList(commentExportDTO);
        when(commentService.deleteCommentDoctor(1L, 1L)).thenReturn(comments);

        List<CommentExportDTO> result = commentController.deleteCommentDoctor(1L, 1L);

        assertEquals(1, result.size());
        assertEquals(commentExportDTO, result.get(0));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    public void testDeleteCommentArticle() {
        List<CommentExportDTO> comments = Collections.singletonList(commentExportDTO);
        when(commentService.deleteCommentArticle(1L, 1L)).thenReturn(comments);

        List<CommentExportDTO> result = commentController.deleteCommentArticle(1L, 1L);

        assertEquals(1, result.size());
        assertEquals(commentExportDTO, result.get(0));
    }
}
