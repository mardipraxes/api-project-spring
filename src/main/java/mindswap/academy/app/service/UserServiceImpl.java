package mindswap.academy.app.service;

import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.exceptions.InvalidRequestException;
import mindswap.academy.app.exceptions.UserNotFoundException;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public List<UserDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(userConverter::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {

        if(id < 1) {
            throw new InvalidRequestException(id.toString());
        }

        return userRepo.findById(id)
                .map(userConverter::toDto)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }
}
