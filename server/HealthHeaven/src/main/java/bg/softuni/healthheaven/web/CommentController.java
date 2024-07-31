package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.commet.CommentDTO;
import bg.softuni.healthheaven.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/doctors/{id}/comments")
    public ResponseEntity<CommentDTO> postComment(@RequestBody @Valid CommentDTO commentDTO,
                                                  @PathVariable Long id) {
        CommentDTO result = commentService.addComment(commentDTO, id);        return ResponseEntity.ok(result);
    }
}
