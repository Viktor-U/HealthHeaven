package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.commet.CommentDTO;
import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import bg.softuni.healthheaven.model.dtos.doctor.DoctorDTO;
import bg.softuni.healthheaven.model.dtos.doctor.DoctorExportDTO;
import bg.softuni.healthheaven.services.CommentService;
import bg.softuni.healthheaven.services.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DoctorController {
    private final DoctorService doctorService;
    private final CommentService commentService;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {

        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorExportDTO> getOneDoctor(@PathVariable Long id) {

        return ResponseEntity.ok(doctorService.getOneDoctor(id));
    }

    @PostMapping("/doctors/{id}/comments")
    public ResponseEntity<CommentDTO> postComment(@RequestBody @Valid CommentDTO commentDTO,
                                                        @PathVariable Long id) {

        CommentDTO result = commentService.addComment(commentDTO, id);

        return ResponseEntity.ok(result);
    }
}
