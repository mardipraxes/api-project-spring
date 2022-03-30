package mindswap.academy.app.converters;

import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.persistance.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {


    public UserDto toDto(User user) {



        return UserDto.builder()
                .username(user.getUsername())
                .country(user.getCountry())
                .email(user.getEmail())
                .type(user.getClass().getName())
                .build();
    }

}
