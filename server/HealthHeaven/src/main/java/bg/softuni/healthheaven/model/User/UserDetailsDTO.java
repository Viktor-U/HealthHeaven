package bg.softuni.healthheaven.model.User;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class UserDetailsDTO extends User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;


    public UserDetailsDTO(String password,
                          Collection<? extends GrantedAuthority> authorities,
                          Long id,
                          String firstName,
                          String lastName,
                          String email) {
        super(email, password, authorities);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        if (firstName != null) {
            fullName.append(firstName);
        }
        if (lastName != null) {
            if (!fullName.isEmpty()) {
                fullName.append(" ");
            }
            fullName.append(lastName);
        }

        return fullName.toString();
    }


}

