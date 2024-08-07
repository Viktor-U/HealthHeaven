package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.config.UserAuthenticationProvider;
import bg.softuni.healthheaven.model.dtos.user.UserDTO;
import bg.softuni.healthheaven.model.dtos.user.UserLoginDTO;
import bg.softuni.healthheaven.model.dtos.user.UserRegisterDTO;
import bg.softuni.healthheaven.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserAuthenticationProvider userAuthenticationProvider;

    @InjectMocks
    private AuthController authController;

    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userDTO = UserDTO.builder()
                .id(1L)
                .firstName("Test")
                .lastName("User")
                .email("testuser@example.com")
                .token("dummyToken")
                .build();
    }

    @Test
    public void testLogin() {
        UserLoginDTO userLoginDTO = new UserLoginDTO("testuser@example.com", "password");
        when(userService.login(any(UserLoginDTO.class))).thenReturn(userDTO);
        when(userAuthenticationProvider.createToken(any(UserDTO.class))).thenReturn("dummyToken");

        ResponseEntity<UserDTO> response = authController.login(userLoginDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userDTO, response.getBody());
        assertEquals("dummyToken", response.getBody().getToken());
    }

    @Test
    public void testRegister() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO("password", "Test", "User", "testuser@example.com");
        when(userService.register(any(UserRegisterDTO.class))).thenReturn(userDTO);
        when(userAuthenticationProvider.createToken(any(UserDTO.class))).thenReturn("dummyToken");

        ResponseEntity<UserDTO> response = authController.register(userRegisterDTO);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(userDTO, response.getBody());
        assertEquals("dummyToken", response.getBody().getToken());
        assertEquals(URI.create("/users/" + userDTO.getId()), response.getHeaders().getLocation());
    }
}
