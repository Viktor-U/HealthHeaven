package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.commet.CommentDTO;
import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import bg.softuni.healthheaven.model.entities.Article;
import bg.softuni.healthheaven.model.entities.Comment;
import bg.softuni.healthheaven.model.entities.Doctor;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.repositories.ArticleRepository;
import bg.softuni.healthheaven.repositories.CommentRepository;
import bg.softuni.healthheaven.repositories.DoctorRepository;
import bg.softuni.healthheaven.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CommentService commentService;

    private Comment comment;
    private CommentDTO commentDTO;
    private CommentExportDTO commentExportDTO;
    private User user;
    private Doctor doctor;
    private Article article;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");

        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setComments(new ArrayList<>());

        article = new Article();
        article.setId(1L);
        article.setComments(new ArrayList<>());

        comment = new Comment();
        comment.setId(1L);
        comment.setContent("Sample comment");
        comment.setAuthor(user);
        comment.setTimeOnPost(Instant.now());

        commentDTO = new CommentDTO();
        commentDTO.setContent("Sample comment");
        commentDTO.setAuthor("johndoe@example.com");

        commentExportDTO = new CommentExportDTO();
        commentExportDTO.setId(1L);
        commentExportDTO.setContent("Sample comment");
        commentExportDTO.setAuthor("John Doe");
        commentExportDTO.setTimeOnPost(Instant.now().toString());
    }

    @Test
    public void testAddCommentDoctor() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(doctor));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
        when(modelMapper.map(any(CommentDTO.class), eq(Comment.class))).thenReturn(comment);
        when(modelMapper.map(any(Comment.class), eq(CommentExportDTO.class))).thenReturn(commentExportDTO);

        CommentExportDTO result = commentService.addCommentDoctor(commentDTO, 1L);

        assertEquals(commentExportDTO, result);
    }

    @Test
    public void testAddCommentArticle() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
        when(modelMapper.map(any(CommentDTO.class), eq(Comment.class))).thenReturn(comment);
        when(modelMapper.map(any(Comment.class), eq(CommentExportDTO.class))).thenReturn(commentExportDTO);

        CommentExportDTO result = commentService.addCommentArticle(commentDTO, 1L);

        assertEquals(commentExportDTO, result);
    }

    @Test
    public void testDeleteCommentDoctor() {
        when(doctorRepository.findById(anyLong())).thenReturn(Optional.of(doctor));

        commentService.deleteCommentDoctor(1L, 1L);

        verify(commentRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteCommentArticle() {
        when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

        commentService.deleteCommentArticle(1L, 1L);

        verify(commentRepository, times(1)).deleteById(1L);
    }
}
