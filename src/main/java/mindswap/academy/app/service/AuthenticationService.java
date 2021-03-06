package mindswap.academy.app.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.JournalistApplicationDto;
import mindswap.academy.app.commands.RegistrationDto;
import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.exceptions.InvalidRequestException;
import mindswap.academy.app.exceptions.UserAlreadyExistsException;
import mindswap.academy.app.persistance.model.Journalist;
import mindswap.academy.app.persistance.model.JournalistApplications;
import mindswap.academy.app.persistance.model.Role;
import mindswap.academy.app.persistance.repository.JournalistApplicationsRepo;
import mindswap.academy.app.persistance.repository.UserRepo;
import mindswap.academy.app.utils.MHasher;
import mindswap.academy.app.utils.StringUtil;
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
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;

    private final UserConverter userConverter;

    private final PasswordEncoder passwordEncoder;

    private final JournalistApplicationsRepo journalistApplicationsRepo;

    private final MHasher mHasher;

    public void registerUser(RegistrationDto registrationDto) {

        if(!StringUtil.isValidPassword(registrationDto.getPassword())) {
            log.warn("Password is not strong enough or it's invalid");
            throw new InvalidRequestException("Password is not strong enough or it's invalid");
        }

        if(!StringUtil.isValidEmail(registrationDto.getEmail())) {
            log.warn("Email is not valid");
            throw new InvalidRequestException("Email is not valid");
        }

        if(userRepo.findByUsername(registrationDto.getUsername()) != null) {
            log.warn("User {} already exists", registrationDto.getUsername());
            throw new UserAlreadyExistsException(registrationDto.getUsername());
        }

        JournalistApplications journalistApplication =
                journalistApplicationsRepo.findByUsername(registrationDto.getUsername());

        if(journalistApplication != null &&
                journalistApplication.getRegistrationToken().equals(registrationDto.getJournalistToken())) {
            journalistApplicationsRepo.delete(journalistApplication);
            String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());
            Journalist journalist =  userConverter
                    .toEntityFromRegistrationDtoJournalist(registrationDto, encodedPassword);
            journalist.setNewsPosts(new ArrayList<>());
            userRepo.save(journalist);
            log.info("User {} successfully registered", registrationDto.getUsername());
            return;
        }

        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());

        userRepo.save(userConverter.toEntityFromRegistrationDto(registrationDto, encodedPassword));

    }

    public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {
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
        if(userRepo.findByUsername(journalistApplicationDto.getUsername()) != null
        || userRepo.findByEmail(journalistApplicationDto.getEmail()) != null) {

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
