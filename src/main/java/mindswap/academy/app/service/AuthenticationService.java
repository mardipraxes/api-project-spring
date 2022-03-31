package mindswap.academy.app.service;


import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.JournalistApplicationDto;
import mindswap.academy.app.commands.RegistrationDto;
import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.exceptions.UserAlreadyExistsException;
import mindswap.academy.app.persistance.model.Role;
import mindswap.academy.app.persistance.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(RegistrationDto registrationDto) {

        if(userRepo.findByUsername(registrationDto.getUsername()) != null) {
            log.warn("User {} already exists", registrationDto.getUsername());
            throw new UserAlreadyExistsException(registrationDto.getUsername());
        }

        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());

        userRepo.save(userConverter.toEntityFromRegistrationDto(registrationDto, encodedPassword));

    }

    public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }

    public void logout() {
        log.info("Logging out");
        SecurityContextHolder.clearContext();
    }

    //Returns a token in case of success, null otherwise

    public String applyJournalist(JournalistApplicationDto journalistApplicationDto) {
        return null;
    }
}
