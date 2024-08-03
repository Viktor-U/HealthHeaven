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
    public ResponseEntity<CommentExportDTO> postComment(@RequestBody @Valid CommentDTO commentDTO,
                                                        @PathVariable Long id) {
        CommentExportDTO result = commentService.addComment(commentDTO, id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/doctors/{id}/comments")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<CommentExportDTO> deleteComment(@RequestParam Long commentId,
                                          @PathVariable Long id) {
        List<CommentExportDTO> commentExportDTO = commentService.deleteComment(commentId, id);
        return commentExportDTO;
    }
}
