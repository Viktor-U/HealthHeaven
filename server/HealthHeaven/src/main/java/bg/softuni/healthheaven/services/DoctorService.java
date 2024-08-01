package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import bg.softuni.healthheaven.model.dtos.doctor.DoctorDTO;
import bg.softuni.healthheaven.model.dtos.doctor.DoctorExportDTO;
import bg.softuni.healthheaven.model.entities.Doctor;
import bg.softuni.healthheaven.repositories.CommentRepository;
import bg.softuni.healthheaven.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.attoparser.dom.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService{

    private final DoctorRepository doctorRepository;

    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;


    public List<DoctorDTO> getAllDoctors() {

        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> result = new ArrayList<>();

        for (Doctor doctor : doctors) {

            result.add(modelMapper.map(doctor, DoctorDTO.class));

        }

        return result;
    }

    public DoctorExportDTO getOneDoctor(Long id) throws Exception {
        DoctorExportDTO result = modelMapper.map(
                doctorRepository.findById(id).orElseThrow(() -> new Exception("Doctor not found")),
                DoctorExportDTO.class
        );
        if (!result.getComments().isEmpty()) {
            for (CommentExportDTO comment : result.getComments()) {
                comment.setAuthor(commentRepository.findById(comment.getId()).get().getAuthor().getFirstName()
                        + " " + commentRepository.findById(comment.getId()).get().getAuthor().getLastName());
            }
        }
        return result;

    }

    public DoctorExportDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        Doctor savedDoctor = doctorRepository.save(doctor);

        return modelMapper.map(savedDoctor, DoctorExportDTO.class);
    }

    public DoctorExportDTO editDoctor(DoctorDTO doctorDTO) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorDTO.getId());
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();

            doctor.setName(doctorDTO.getName());
            doctor.setSpecialization(doctorDTO.getSpecialization());
            doctor.setProfilePictureURL(doctorDTO.getProfilePictureURL());
            doctor.setPhoneNumber(doctorDTO.getPhoneNumber());
            doctor.setDescription(doctorDTO.getDescription());
            doctorRepository.save(doctor);

            return modelMapper.map(doctor, DoctorExportDTO.class);
        }
        return null;

    }
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

}
