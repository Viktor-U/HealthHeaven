package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.IdRequestDTO;
import bg.softuni.healthheaven.model.dtos.commet.CommentDTO;
import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import bg.softuni.healthheaven.model.dtos.doctor.DoctorExportDTO;
import bg.softuni.healthheaven.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/doctors/{id}/comments")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<CommentExportDTO> postCommentDoctor(@RequestBody @Valid CommentDTO commentDTO,
                                                        @PathVariable Long id) {
        CommentExportDTO result = commentService.addCommentDoctor(commentDTO, id);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/articles/{id}/comments")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<CommentExportDTO> postCommentArticle(@RequestBody @Valid CommentDTO commentDTO,
                                                        @PathVariable Long id) {
        CommentExportDTO result = commentService.addCommentArticle(commentDTO, id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/doctors/{id}/comments")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<CommentExportDTO> deleteCommentDoctor(@RequestParam Long commentId,
                                          @PathVariable Long id) {
        return commentService.deleteCommentDoctor(commentId, id);
    }
    @DeleteMapping("/articles/{id}/comments")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<CommentExportDTO> deleteCommentArticle(@RequestParam Long commentId,
                                                      @PathVariable Long id) {
        return commentService.deleteCommentArticle(commentId, id);
    }
}
