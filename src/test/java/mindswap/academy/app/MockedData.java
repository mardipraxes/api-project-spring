package mindswap.academy.app;

import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.persistance.model.User;

public class MockedData {


    public static User getUserEntity() {
        return User.builder()
                .id(1L)
                .username("user")
                .email("john@john.com")
                .build();
    }
    public static UserDto getUserDto() {
        return UserDto.builder()
                .username("user")
                .email("john@john.com")
                .build();

    }
}
