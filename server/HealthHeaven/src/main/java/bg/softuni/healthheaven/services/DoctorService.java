package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.doctor.DoctorDTO;
import bg.softuni.healthheaven.model.entities.Doctor;
import bg.softuni.healthheaven.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService{

    private final DoctorRepository doctorRepository;

    private final ModelMapper modelMapper;


    public List<DoctorDTO> getAllDoctors() {

        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> result = new ArrayList<>();

        for (Doctor doctor : doctors) {
            result.add(modelMapper.map(doctor, DoctorDTO.class));
        }

        return result;
    }
}
