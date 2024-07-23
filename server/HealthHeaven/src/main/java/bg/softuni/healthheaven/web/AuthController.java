package bg.softuni.healthheaven.web;

import bg.softuni.healthheaven.model.dtos.UserRegisterDTO;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.model.entities.UserRole;
import bg.softuni.healthheaven.model.enums.RoleEnum;
import bg.softuni.healthheaven.repositories.UserRepository;
import bg.softuni.healthheaven.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController implements UserDetailsService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public String saveEmployee(@RequestBody UserRegisterDTO userRegisterDTO)
    {
        String id = userService.registerUser(userRegisterDTO);
        return id;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

