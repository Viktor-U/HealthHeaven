package bg.softuni.healthheaven.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {
    @NotBlank
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 symbols!")
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 20, message = "Last name should be between 2 and 20 symbols!")
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 3)
    private String password;
    @NotEmpty
    @Size(min = 3)
    private String confirmPassword;
}
