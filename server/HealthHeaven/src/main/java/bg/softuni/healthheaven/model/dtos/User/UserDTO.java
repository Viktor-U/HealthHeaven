package bg.softuni.healthheaven.model.dtos.user;


import bg.softuni.healthheaven.model.enums.RoleEnum;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private RoleEnum role;
}