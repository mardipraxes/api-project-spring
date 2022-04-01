package mindswap.academy.app.unit;

import mindswap.academy.app.MockedData;
import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.UserRepo;
import mindswap.academy.app.service.UserService;
import mindswap.academy.app.service.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    private UserServiceImpl underTest;

    @Mock
    private UserConverter userConverter;

    @Mock
    private MockedData mockedData;




    @BeforeEach
    void setUp() {
       this.underTest = new UserServiceImpl(userRepo, userConverter);
    }


    @Test
    @Disabled
    void loadUserByUsername() {
    }

    @Test
    void canGetAllUsers() {

    }



    @Test

    void canGetUserById() {

    }



    @AfterEach
    void tearDown() {
    }



    @Test
    @Disabled
    void getCurrentUser() {
        String username = "joao";
        //given
        User user = new User();
        user.setUsername(username);
        when (userRepo.findByUsername(username)).thenReturn(user);
        //when
         UserDto userDto = underTest.getCurrentUser();
        //then
        assertEquals(username, user.getUsername());
    }

    public static User getUserEntity() {
        return User.builder()
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