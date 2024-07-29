package bg.softuni.healthheaven.model.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDTO {

    @NotBlank
    private String email;

    @NotEmpty
    private char[] password;
}
