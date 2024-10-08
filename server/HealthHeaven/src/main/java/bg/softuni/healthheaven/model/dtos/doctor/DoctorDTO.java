package bg.softuni.healthheaven.model.dtos.doctor;

import bg.softuni.healthheaven.model.dtos.commet.CommentDTO;
import bg.softuni.healthheaven.model.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDTO {

    private long id;

    private String name;

    private String specialization;

    private String phoneNumber;

    private String profilePictureURL;

    private String description;

}
