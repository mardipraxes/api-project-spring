package mindswap.academy.app.unit.converters;

import mindswap.academy.app.MockData;
import mindswap.academy.app.commands.RegistrationDto;
import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.persistance.model.Journalist;
import mindswap.academy.app.persistance.model.Role;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.CategoryRepo;
import mindswap.academy.app.persistance.repository.RatingRepo;
import mindswap.academy.app.persistance.repository.RoleRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserConverterTest {

    private UserConverter userConverterTest;

    @Mock
    private final RoleRepo roleRepo = mock(RoleRepo.class);


    @BeforeEach
    public void setup() {
        userConverterTest = new UserConverter(roleRepo);
    }


    @Test
    public void testConvertToDto() {
        // Given
        User user = MockData.getMockUser();

        // When
        UserDto userDto = userConverterTest.toDto(user);

        // Then
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());
    }

    @Test
    public void testConvertToEntityFromRegistrationDto() {
        // Given
        RegistrationDto registrationDto = MockData.getMockRegistrationDto();

        when(roleRepo.findByName("ROLE_User")).thenReturn(new Role("ROLE_User"));

        // When
        User user = userConverterTest.toEntityFromRegistrationDto(registrationDto, "test");

        // Then
        assertEquals(user.getUsername(),registrationDto.getUsername());
        assertEquals(user.getEmail(),registrationDto.getEmail());
        assertEquals("ROLE_User",user.getRoles().stream().findFirst().get().getName());

    }

    @Test
    public void testRegisterJournalistFromRegistrationDto() {
        // Given
        RegistrationDto registrationDto = MockData.getMockRegistrationDto();

        when(roleRepo.findByName("ROLE_User")).thenReturn(new Role("ROLE_User"));
        when(roleRepo.findByName("ROLE_Journalist")).thenReturn(new Role("ROLE_Journalist"));

        // When
        User user = userConverterTest.toEntityFromRegistrationDtoJournalist(registrationDto, "test");

        // Then
        assertEquals(user.getUsername(),registrationDto.getUsername());
        assertEquals(user.getEmail(),registrationDto.getEmail());
        assertEquals("ROLE_Journalist",
                user.getRoles().stream()
                        .filter(role -> role.getName().equals("ROLE_Journalist"))
                        .findFirst().get().getName());

        assertInstanceOf(Journalist.class, user);

    }


}
