package mindswap.academy.app.converters;

import mindswap.academy.app.commands.RegistrationDto;
import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.persistance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto toDto(User user) {



        return UserDto.builder()
                .username(user.getUsername())
                .country(user.getCountry())
                .email(user.getEmail())
                .type(user.getClass().getName())
                .build();
    }

//    public User toEntity(UserDto userDto) {
//        User user = User.builder()
//                .username(userDto.getUsername())
//                .country(userDto.getCountry())
//                .password(userDto.getPassword())
//
//        return user;
//    }

    public User toEntityFromRegistrationDto(RegistrationDto registrationDto) {
        return User.builder()
                .username(registrationDto.getUsername())
                .country(registrationDto.getCountry())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .email(registrationDto.getEmail())
                .build();
    }
}
