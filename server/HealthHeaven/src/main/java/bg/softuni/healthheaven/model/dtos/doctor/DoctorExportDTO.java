package bg.softuni.healthheaven.model.dtos.doctor;

import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorExportDTO {

    private long id;

    private String name;

    private String specialization;

    private String phoneNumber;

    private String profilePictureURL;

    private String description;

    private List<CommentExportDTO> comments = new ArrayList<>();

}