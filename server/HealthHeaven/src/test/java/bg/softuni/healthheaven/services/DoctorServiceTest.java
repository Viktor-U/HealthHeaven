package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import bg.softuni.healthheaven.model.dtos.doctor.DoctorDTO;
import bg.softuni.healthheaven.model.dtos.doctor.DoctorExportDTO;
import bg.softuni.healthheaven.model.entities.Doctor;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.repositories.CommentRepository;
import bg.softuni.healthheaven.repositories.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DoctorService doctorService;

    private Doctor doctor;
    private DoctorDTO doctorDTO;
    private DoctorExportDTO doctorExportDTO;
    private CommentExportDTO commentExportDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. John Doe");
        doctor.setSpecialization("Cardiology");
        doctor.setProfilePictureURL("http://example.com/image.jpg");
        doctor.setPhoneNumber("123-456-7890");
        doctor.setDescription("Experienced cardiologist");
        doctor.setComments(new ArrayList<>());

        doctorDTO = new DoctorDTO();
        doctorDTO.setId(1L);
        doctorDTO.setName("Dr. John Doe");
        doctorDTO.setSpecialization("Cardiology");
        doctorDTO.setProfilePictureURL("http://example.com/image.jpg");
        doctorDTO.setPhoneNumber("123-456-7890");
        doctorDTO.setDescription("Experienced cardiologist");

        commentExportDTO = new CommentExportDTO();
        commentExportDTO.setId(1L);
        commentExportDTO.setContent("Great doctor!");
        commentExportDTO.setAuthor("Jane Smith");

        doctorExportDTO = new DoctorExportDTO();
        doctorExportDTO.setId(1L);
        doctorExportDTO.setName("Dr. John Doe");
        doctorExportDTO.setSpecialization("Cardiology");
        doctorExportDTO.setProfilePictureURL("http://example.com/image.jpg");
        doctorExportDTO.setPhoneNumber("123-456-7890");
        doctorExportDTO.setDescription("Experienced cardiologist");
        doctorExportDTO.setComments(Collections.singletonList(commentExportDTO));
    }

    @Test
    public void testGetAllDoctors() {
        when(doctorRepository.findAll()).thenReturn(Collections.singletonList(doctor));
        when(modelMapper.map(any(Doctor.class), eq(DoctorDTO.class))).thenReturn(doctorDTO);

        List<DoctorDTO> result = doctorService.getAllDoctors();

        assertEquals(1, result.size());
        assertEquals(doctorDTO, result.get(0));
    }


    @Test
    public void testGetOneDoctorNotFound() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            doctorService.getOneDoctor(1L);
        });

        assertEquals("Doctor not found", exception.getMessage());
    }

    @Test
    public void testCreateDoctor() {
        when(modelMapper.map(any(DoctorDTO.class), eq(Doctor.class))).thenReturn(doctor);
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);
        when(modelMapper.map(any(Doctor.class), eq(DoctorExportDTO.class))).thenReturn(doctorExportDTO);

        DoctorExportDTO result = doctorService.createDoctor(doctorDTO);

        assertEquals(doctorExportDTO, result);
    }

    @Test
    public void testEditDoctor() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);
        when(modelMapper.map(any(Doctor.class), eq(DoctorExportDTO.class))).thenReturn(doctorExportDTO);

        DoctorExportDTO result = doctorService.editDoctor(doctorDTO);

        assertEquals(doctorExportDTO, result);
    }

    @Test
    public void testEditDoctorNotFound() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.empty());

        DoctorExportDTO result = doctorService.editDoctor(doctorDTO);

        assertEquals(null, result);
    }

    @Test
    public void testDeleteDoctor() {
        doNothing().when(doctorRepository).deleteById(1L);

        doctorService.deleteDoctor(1L);

        verify(doctorRepository, times(1)).deleteById(1L);
    }
}
