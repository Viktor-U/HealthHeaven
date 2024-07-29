package bg.softuni.healthheaven.config;

import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.model.enums.RoleEnum;
import bg.softuni.healthheaven.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public User dataSourceInitializer(UserRepository userRepository,
                                      PasswordEncoder passwordEncoder) {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setFirstName("Viktor");
            user.setLastName("Uzunov");
            user.setEmail("VIKI@mail.com");
            user.setRole(RoleEnum.ADMIN);
            user.setPassword(passwordEncoder.encode("1111"));
           return userRepository.save(user);
        }

        return null;
    }
}
