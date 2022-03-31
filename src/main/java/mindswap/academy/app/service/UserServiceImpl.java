package mindswap.academy.app.service;

import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.PasswordDto;
import mindswap.academy.app.commands.RegistrationDto;
import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.exceptions.InvalidRequestException;
import mindswap.academy.app.exceptions.UserAlreadyExistsException;
import mindswap.academy.app.exceptions.UserNotFoundException;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            log.warn("User {} not found", username);
            throw new UserNotFoundException(username);
        }

        log.info("User {} is trying to log in..", username);

        Collection<SimpleGrantedAuthority> authorities =
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .toList();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public List<UserDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(userConverter::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {

        if (id < 1) {
            log.warn("Invalid user id {}", id);
            throw new InvalidRequestException(id.toString());
        }

        return userRepo.findById(id)
                .map(userConverter::toDto)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    public UserDto getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("User {} is trying to get his profile", username);
        return userConverter.toDto(userRepo.findByUsername(username));
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
