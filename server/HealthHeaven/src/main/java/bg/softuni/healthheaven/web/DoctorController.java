package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.commet.CommentDTO;

import bg.softuni.healthheaven.model.dtos.doctor.DoctorDTO;
import bg.softuni.healthheaven.model.dtos.doctor.DoctorExportDTO;
import bg.softuni.healthheaven.services.CommentService;
import bg.softuni.healthheaven.services.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
public class DoctorController {
    private final DoctorService doctorService;


    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {

        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorExportDTO> getOneDoctor(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(doctorService.getOneDoctor(id));
    }

    @PostMapping("/doctors")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DoctorExportDTO> createDoctor(@RequestBody @Valid DoctorDTO doctorDTO) {
        DoctorExportDTO result = doctorService.createDoctor(doctorDTO);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/doctors/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);

    }


}
