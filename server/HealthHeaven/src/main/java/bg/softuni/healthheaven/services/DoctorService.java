package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService{

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
}
