package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.exceptions.AppException;
import bg.softuni.healthheaven.model.dtos.user.UserDTO;
import bg.softuni.healthheaven.model.dtos.user.UserLoginDTO;
import bg.softuni.healthheaven.model.dtos.user.UserRegisterDTO;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.model.enums.RoleEnum;
import bg.softuni.healthheaven.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public UserDTO login(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByEmail(userLoginDTO.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(userLoginDTO.getPassword()), user.getPassword())) {
            return modelMapper.map(user, UserDTO.class);

        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDTO register(UserRegisterDTO userRegisterDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(userRegisterDTO.getEmail());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setRole(RoleEnum.USER);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userRegisterDTO.getPassword())));

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    public UserDTO findByLogin(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        return modelMapper.map(user, UserDTO.class);
    }
}
