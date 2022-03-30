package mindswap.academy.app.converters;

import mindswap.academy.app.commands.RegistrationDto;
import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.persistance.model.Role;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserConverter {

    @Autowired
    private RoleRepo roleRepo;


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

    public User toEntityFromRegistrationDto(RegistrationDto registrationDto, String encodedPassword) {
        Set<Role> roles = new HashSet<>();

        roles.add(roleRepo.findByName("ROLE_User"));

        if(registrationDto.getAdminToken().equals("abcde")){
            roles.add(roleRepo.findByName("ROLE_Admin"));
            roles.add(roleRepo.findByName("ROLE_Journalist"));
        }

        return User.builder()
                .username(registrationDto.getUsername())
                .roles(roles)
                .country(registrationDto.getCountry())
                .password(encodedPassword)
                .email(registrationDto.getEmail())
                .build();
    }
}
