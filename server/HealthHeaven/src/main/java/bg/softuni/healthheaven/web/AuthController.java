package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.config.UserAuthenticationProvider;
import bg.softuni.healthheaven.model.dtos.user.UserDTO;
import bg.softuni.healthheaven.model.dtos.user.UserLoginDTO;
import bg.softuni.healthheaven.model.dtos.user.UserRegisterDTO;
import bg.softuni.healthheaven.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        UserDTO userDTO = userService.login(userLoginDTO);
        userDTO.setToken(userAuthenticationProvider.createToken(userDTO));
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserRegisterDTO user) {
        UserDTO createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }


}

