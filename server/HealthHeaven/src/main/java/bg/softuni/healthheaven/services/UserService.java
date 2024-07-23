package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.UserRegisterDTO;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.model.entities.UserRole;
import bg.softuni.healthheaven.model.enums.RoleEnum;
import bg.softuni.healthheaven.repositories.UserRepository;
import bg.softuni.healthheaven.repositories.UserRoleRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository
//            , PasswordEncoder passwordEncoder
            , UserRoleRepository userRoleRepository, Gson gson, ModelMapper modelMapper) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }



    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public String registerUser(UserRegisterDTO userRegisterDTO) {


        userRepository.save(map(userRegisterDTO));
        return userRegisterDTO.getFirstName();
    }

    private User map(UserRegisterDTO userRegisterDTO) {

        User user = modelMapper.map(userRegisterDTO, User.class);
//        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        UserRole userRole = userRoleRepository.findByRole(RoleEnum.USER);
        user.getRoles().add(userRole);

        return user;
    }
}
