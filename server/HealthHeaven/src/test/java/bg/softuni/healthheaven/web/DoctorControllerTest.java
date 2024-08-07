package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.doctor.DoctorDTO;
import bg.softuni.healthheaven.model.dtos.doctor.DoctorExportDTO;
import bg.softuni.healthheaven.services.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DoctorControllerTest {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    private DoctorDTO doctorDTO;
    private DoctorExportDTO doctorExportDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        doctorDTO = new DoctorDTO();
        doctorDTO.setId(1L);
        doctorDTO.setName("Dr. John Doe");
        doctorDTO.setSpecialization("Cardiology");
        doctorDTO.setProfilePictureURL("http://example.com/image.jpg");
        doctorDTO.setPhoneNumber("123-456-7890");
        doctorDTO.setDescription("Experienced cardiologist");

        doctorExportDTO = new DoctorExportDTO();
        doctorExportDTO.setId(1L);
        doctorExportDTO.setName("Dr. John Doe");
        doctorExportDTO.setSpecialization("Cardiology");
        doctorExportDTO.setProfilePictureURL("http://example.com/image.jpg");
        doctorExportDTO.setPhoneNumber("123-456-7890");
        doctorExportDTO.setDescription("Experienced cardiologist");
    }

    @Test
    public void testGetAllDoctors() {
        when(doctorService.getAllDoctors()).thenReturn(Collections.singletonList(doctorDTO));

        ResponseEntity<List<DoctorDTO>> response = doctorController.getAllDoctors();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(doctorDTO, response.getBody().get(0));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    public void testGetOneDoctor() throws Exception {
        when(doctorService.getOneDoctor(1L)).thenReturn(doctorExportDTO);

        ResponseEntity<DoctorExportDTO> response = doctorController.getOneDoctor(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(doctorExportDTO, response.getBody());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void testCreateDoctor() {
        when(doctorService.createDoctor(any(DoctorDTO.class))).thenReturn(doctorExportDTO);

        ResponseEntity<DoctorExportDTO> response = doctorController.createDoctor(doctorDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(doctorExportDTO, response.getBody());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void testEditDoctor() {
        when(doctorService.editDoctor(any(DoctorDTO.class))).thenReturn(doctorExportDTO);

        ResponseEntity<DoctorExportDTO> response = doctorController.editDoctor(doctorDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(doctorExportDTO, response.getBody());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void testDeleteDoctor() {
        doNothing().when(doctorService).deleteDoctor(1L);

        doctorController.deleteDoctor(1L);

        verify(doctorService, times(1)).deleteDoctor(1L);
    }
}
