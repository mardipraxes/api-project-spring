package mindswap.academy.app.service;


import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.JournalistApplicationDto;
import mindswap.academy.app.commands.RegistrationDto;
import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.exceptions.UserAlreadyExistsException;
import mindswap.academy.app.persistance.model.Journalist;
import mindswap.academy.app.persistance.model.JournalistApplications;
import mindswap.academy.app.persistance.model.Role;
import mindswap.academy.app.persistance.repository.JournalistApplicationsRepo;
import mindswap.academy.app.persistance.repository.UserRepo;
import mindswap.academy.app.utils.MHasher;
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

    @Autowired
    private JournalistApplicationsRepo journalistApplicationsRepo;

    @Autowired
    private MHasher mHasher;

    public void registerUser(RegistrationDto registrationDto) {

        if(userRepo.findByUsername(registrationDto.getUsername()) != null) {
            log.warn("User {} already exists", registrationDto.getUsername());
            throw new UserAlreadyExistsException(registrationDto.getUsername());
        }

        JournalistApplications journalistApplications =
                journalistApplicationsRepo.findByUsername(registrationDto.getUsername());

        if(journalistApplications != null &&
                journalistApplications.getRegistrationToken().equals(registrationDto.getJournalistToken())) {
            journalistApplicationsRepo.delete(journalistApplications);
            String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());
            Journalist journalist = (Journalist) userConverter
                    .toEntityFromRegistrationDtoJournalist(registrationDto, encodedPassword);
            journalist.setNewsPosts(new ArrayList<>());
            userRepo.save(journalist);

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
        if(userRepo.findByUsername(journalistApplicationDto.getUsername()) != null) {
            log.warn("User {} already exists", journalistApplicationDto.getUsername());
            throw new UserAlreadyExistsException(journalistApplicationDto.getUsername());
        }

        JournalistApplications journalistApplications = JournalistApplications.builder()
                .username(journalistApplicationDto.getUsername())
                .build();

        String token = mHasher.getToken(journalistApplicationDto.getUsername());

        journalistApplications.setRegistrationToken(token);

        journalistApplicationsRepo.save(journalistApplications);

        return token;
    }
}
