package bg.softuni.healthheaven.model.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDTO {
    @NotBlank
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 symbols!")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20, message = "Last name should be between 2 and 20 symbols!")
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

}
