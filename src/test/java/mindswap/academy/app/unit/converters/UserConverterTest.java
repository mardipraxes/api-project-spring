package mindswap.academy.app.unit.converters;

import mindswap.academy.app.MockData;
import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.persistance.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserConverterTest {

    private final UserConverter userConverter = new UserConverter();


    @Test
    public void testConvertToDto() {
        // Given
        User user = MockData.getMockUser();

        // When
        UserDto userDto = userConverter.toDto(user);

        // Then
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());
    }


}
