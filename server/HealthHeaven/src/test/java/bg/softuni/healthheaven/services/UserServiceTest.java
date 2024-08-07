package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.exceptions.AppException;
import bg.softuni.healthheaven.model.dtos.user.UserDTO;
import bg.softuni.healthheaven.model.dtos.user.UserLoginDTO;
import bg.softuni.healthheaven.model.dtos.user.UserRegisterDTO;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.model.enums.RoleEnum;
import bg.softuni.healthheaven.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;
    private UserLoginDTO userLoginDTO;
    private UserRegisterDTO userRegisterDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("encodedPassword");
        user.setRole(RoleEnum.USER);

        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setEmail("johndoe@example.com");

        userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("johndoe@example.com");
        userLoginDTO.setPassword("password");

        userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setFirstName("John");
        userRegisterDTO.setLastName("Doe");
        userRegisterDTO.setEmail("johndoe@example.com");
        userRegisterDTO.setPassword("password");
    }

    @Test
    public void testLoginSuccess() {
        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userDTO);

        UserDTO result = userService.login(userLoginDTO);

        assertEquals(userDTO, result);
    }

    @Test
    public void testLoginUnknownUser() {
        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.empty());

        AppException exception = assertThrows(AppException.class, () -> {
            userService.login(userLoginDTO);
        });

        assertEquals("Unknown user", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void testLoginInvalidPassword() {
        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(false);

        AppException exception = assertThrows(AppException.class, () -> {
            userService.login(userLoginDTO);
        });

        assertEquals("Invalid password", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    public void testRegisterSuccess() {
        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.empty());
        when(modelMapper.map(any(UserRegisterDTO.class), eq(User.class))).thenReturn(user);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userDTO);

        UserDTO result = userService.register(userRegisterDTO);

        assertEquals(userDTO, result);
    }

    @Test
    public void testRegisterUserExists() {
        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.of(user));

        AppException exception = assertThrows(AppException.class, () -> {
            userService.register(userRegisterDTO);
        });

        assertEquals("User with this email already exists", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    public void testFindByLoginSuccess() {
        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.of(user));
        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userDTO);

        UserDTO result = userService.findByLogin("johndoe@example.com");

        assertEquals(userDTO, result);
    }

    @Test
    public void testFindByLoginUnknownUser() {
        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(Optional.empty());

        AppException exception = assertThrows(AppException.class, () -> {
            userService.findByLogin("johndoe@example.com");
        });

        assertEquals("Unknown user", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
}
