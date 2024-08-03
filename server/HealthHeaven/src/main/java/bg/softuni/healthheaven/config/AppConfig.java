package bg.softuni.healthheaven.config;

import bg.softuni.healthheaven.model.entities.Item;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.model.enums.RoleEnum;
import bg.softuni.healthheaven.repositories.ItemRepository;
import bg.softuni.healthheaven.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


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
                                      PasswordEncoder passwordEncoder,
                                      ItemRepository itemRepository) {

        if (itemRepository.count() == 0) {
            Item item = new Item();
            item.setName("Apple");
            item.setPrice(12.00);
            item.setDescription("Sample Item Description");
            item.setImageURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2QmkGwmo2Bnu2Dg_-hfMsZiF_TIoIjYj2BQ&s");

            Item item2 = new Item();
            item2.setName("Apple Item");
            item2.setPrice(144.00);
            item2.setDescription("Sample Item Description mnogo para");
            item2.setImageURL("https://bunt.bg/wp-content/uploads/2022/03/112922961_apple.jpg");

            itemRepository.saveAll(List.of(item, item2));
        }
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
