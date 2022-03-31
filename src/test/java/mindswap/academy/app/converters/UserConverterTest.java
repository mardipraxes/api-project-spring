package mindswap.academy.app.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;

import mindswap.academy.app.commands.RegistrationDto;

import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.persistance.model.Journalist;
import mindswap.academy.app.persistance.model.Role;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.RoleRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserConverter.class})
@ExtendWith(SpringExtension.class)
class UserConverterTest {
    @MockBean
    private RoleRepo roleRepo;

    @Autowired
    private UserConverter userConverter;

    @Test
    void testToDto() {
        User user = new User();
        user.setCountry("GB");
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsername("janedoe");
        UserDto actualToDtoResult = this.userConverter.toDto(user);
        assertEquals("GB", actualToDtoResult.getCountry());
        assertEquals("janedoe", actualToDtoResult.getUsername());
        assertEquals("mindswap.academy.app.persistance.model.User", actualToDtoResult.getType());
        assertEquals("jane.doe@example.org", actualToDtoResult.getEmail());
    }

    @Test
    void testToDto2() {
        Journalist journalist = mock(Journalist.class);
        when(journalist.getCountry()).thenReturn("GB");
        when(journalist.getEmail()).thenReturn("jane.doe@example.org");
        when(journalist.getUsername()).thenReturn("janedoe");
        doNothing().when(journalist).setCountry((String) any());
        doNothing().when(journalist).setEmail((String) any());
        doNothing().when(journalist).setId((Long) any());
        doNothing().when(journalist).setPassword((String) any());
        doNothing().when(journalist).setRoles((java.util.Set<Role>) any());
        doNothing().when(journalist).setUsername((String) any());
        journalist.setCountry("GB");
        journalist.setEmail("jane.doe@example.org");
        journalist.setId(123L);
        journalist.setPassword("iloveyou");
        journalist.setRoles(new HashSet<>());
        journalist.setUsername("janedoe");
        UserDto actualToDtoResult = this.userConverter.toDto(journalist);
        assertEquals("GB", actualToDtoResult.getCountry());
        assertEquals("janedoe", actualToDtoResult.getUsername());
        assertEquals("mindswap.academy.app.persistance.model.Journalist$MockitoMock$1476077119",
                actualToDtoResult.getType());
        assertEquals("jane.doe@example.org", actualToDtoResult.getEmail());
        verify(journalist).getCountry();
        verify(journalist).getEmail();
        verify(journalist).getUsername();
        verify(journalist).setCountry((String) any());
        verify(journalist).setEmail((String) any());
        verify(journalist).setId((Long) any());
        verify(journalist).setPassword((String) any());
        verify(journalist).setRoles((java.util.Set<Role>) any());
        verify(journalist).setUsername((String) any());
    }

    @Test
    void testToEntityFromRegistrationDto() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        role.setUsers(new ArrayList<>());
        when(this.roleRepo.findByName((String) any())).thenReturn(role);
        User actualToEntityFromRegistrationDtoResult = this.userConverter.toEntityFromRegistrationDto(
                new RegistrationDto("janedoe", "iloveyou", "jane.doe@example.org", "iloveyou", "ABC123", "ABC123", "GB"),
                "secret");
        assertEquals("GB", actualToEntityFromRegistrationDtoResult.getCountry());
        assertEquals("janedoe", actualToEntityFromRegistrationDtoResult.getUsername());
        assertEquals(1, actualToEntityFromRegistrationDtoResult.getRoles().size());
        assertEquals("secret", actualToEntityFromRegistrationDtoResult.getPassword());
        assertNull(actualToEntityFromRegistrationDtoResult.getId());
        assertEquals("jane.doe@example.org", actualToEntityFromRegistrationDtoResult.getEmail());
        verify(this.roleRepo).findByName((String) any());
    }

    @Test
    void testToEntityFromRegistrationDto2() {
        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        role.setUsers(new ArrayList<>());
        when(this.roleRepo.findByName((String) any())).thenReturn(role);
        User actualToEntityFromRegistrationDtoResult = this.userConverter.toEntityFromRegistrationDto(
                new RegistrationDto("janedoe", "iloveyou", "jane.doe@example.org", "iloveyou", "ABC123", "abcde", "GB"),
                "secret");
        assertEquals("GB", actualToEntityFromRegistrationDtoResult.getCountry());
        assertEquals("janedoe", actualToEntityFromRegistrationDtoResult.getUsername());
        assertEquals(1, actualToEntityFromRegistrationDtoResult.getRoles().size());
        assertEquals("secret", actualToEntityFromRegistrationDtoResult.getPassword());
        assertNull(actualToEntityFromRegistrationDtoResult.getId());
        assertEquals("jane.doe@example.org", actualToEntityFromRegistrationDtoResult.getEmail());
        verify(this.roleRepo, atLeast(1)).findByName((String) any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testToEntityFromRegistrationDto3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.equals(Object)" because "obj" is null
        //       at mindswap.academy.app.converters.UserConverter.toEntityFromRegistrationDto(UserConverter.java:45)
        //   In order to prevent toEntityFromRegistrationDto(RegistrationDto, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   toEntityFromRegistrationDto(RegistrationDto, String).
        //   See https://diff.blue/R013 to resolve this issue.

        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        role.setUsers(new ArrayList<>());
        when(this.roleRepo.findByName((String) any())).thenReturn(role);
        this.userConverter.toEntityFromRegistrationDto(new RegistrationDto(), "secret");
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testToEntityFromRegistrationDto4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "mindswap.academy.app.commands.RegistrationDto.getAdminToken()" because "registrationDto" is null
        //       at mindswap.academy.app.converters.UserConverter.toEntityFromRegistrationDto(UserConverter.java:45)
        //   In order to prevent toEntityFromRegistrationDto(RegistrationDto, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   toEntityFromRegistrationDto(RegistrationDto, String).
        //   See https://diff.blue/R013 to resolve this issue.

        Role role = new Role();
        role.setId(123L);
        role.setName("Name");
        role.setUsers(new ArrayList<>());
        when(this.roleRepo.findByName((String) any())).thenReturn(role);
        this.userConverter.toEntityFromRegistrationDto(null, "secret");
    }
}

