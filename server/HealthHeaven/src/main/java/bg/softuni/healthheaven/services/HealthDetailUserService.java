package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.User.UserDetailsDTO;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.model.entities.UserRole;
import bg.softuni.healthheaven.model.enums.RoleEnum;
import bg.softuni.healthheaven.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class HealthDetailUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public HealthDetailUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(HealthDetailUserService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    private static UserDetails map(User user) {
        return new UserDetailsDTO(
                user.getPassword(),
                user.getRoles().stream().map(UserRole::getRole).map(HealthDetailUserService::map).toList(),
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    private static GrantedAuthority map(RoleEnum role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role
        );
    }
}
