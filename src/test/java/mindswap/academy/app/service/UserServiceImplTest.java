/*package mindswap.academy.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import mindswap.academy.app.commands.UserDto;

import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.exceptions.InvalidRequestException;
import mindswap.academy.app.exceptions.UserNotFoundException;
import mindswap.academy.app.persistance.model.Journalist;
import mindswap.academy.app.persistance.model.Role;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserConverter userConverter;

    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        //GIVEN
        User user = new User();
        user.setCountry("GB");
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        //WHEN
        user.setUsername("janedoe");
        when(this.userRepo.findByUsername((String) any())).thenReturn(user);
        UserDetails actualLoadUserByUsernameResult = this.userServiceImpl.loadUserByUsername("janedoe");
        //THEN
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(this.userRepo).findByUsername((String) any());
    }

    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        //GIVEN
        Journalist journalist = mock(Journalist.class);
        journalist.setCountry("GB");
        journalist.setEmail("jane.doe@example.org");
        journalist.setId(123L);
        journalist.setPassword("iloveyou");
        journalist.setRoles(new HashSet<>());
        journalist.setUsername("janedoe");
        //WHEN
        when(journalist.getPassword()).thenReturn("iloveyou");
        when(journalist.getUsername()).thenReturn("janedoe");
        when(journalist.getRoles()).thenReturn(new HashSet<>());
        doNothing().when(journalist).setCountry((String) any());
        doNothing().when(journalist).setEmail((String) any());
        doNothing().when(journalist).setId((Long) any());
        doNothing().when(journalist).setPassword((String) any());
        doNothing().when(journalist).setRoles((Set<Role>) any());
        doNothing().when(journalist).setUsername((String) any());
        when(this.userRepo.findByUsername((String) any())).thenReturn(journalist);
        //THEN
        UserDetails actualLoadUserByUsernameResult = this.userServiceImpl.loadUserByUsername("janedoe");
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(this.userRepo).findByUsername((String) any());
        verify(journalist).getPassword();
        verify(journalist).getUsername();
        verify(journalist).getRoles();
        verify(journalist).setCountry((String) any());
        verify(journalist).setEmail((String) any());
        verify(journalist).setId((Long) any());
        verify(journalist).setPassword((String) any());
        verify(journalist).setRoles((Set<Role>) any());
        verify(journalist).setUsername((String) any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Cannot pass null or empty values to constructor
        //       at org.springframework.util.Assert.isTrue(Assert.java:121)
        //       at org.springframework.security.core.userdetails.User.<init>(User.java:110)
        //       at org.springframework.security.core.userdetails.User.<init>(User.java:87)
        //       at mindswap.academy.app.service.UserServiceImpl.loadUserByUsername(UserServiceImpl.java:54)
        //   In order to prevent loadUserByUsername(String)
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   loadUserByUsername(String).
        //   See https://diff.blue/R013 to resolve this issue.

        Journalist journalist = mock(Journalist.class);
        when(journalist.getPassword()).thenReturn(null);
        when(journalist.getUsername()).thenReturn("janedoe");
        when(journalist.getRoles()).thenReturn(new HashSet<>());
        doNothing().when(journalist).setCountry((String) any());
        doNothing().when(journalist).setEmail((String) any());
        doNothing().when(journalist).setId((Long) any());
        doNothing().when(journalist).setPassword((String) any());
        doNothing().when(journalist).setRoles((java.util.Set<Role>) any());
        doNothing().when(journalist).setUsername((String) any());


        when(this.userRepo.findByUsername((String) any())).thenReturn(journalist);
        this.userServiceImpl.loadUserByUsername("janedoe");
    }

    @Test
    void testLoadUserByUsername4() throws UsernameNotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setName("User {} is trying to log in..");
        role.setUsers(new ArrayList<>());
        Journalist journalist = mock(Journalist.class);

        //GIVEN
        journalist.setCountry("GB");
        journalist.setEmail("jane.doe@example.org");
        journalist.setId(123L);
        journalist.setPassword("iloveyou");
        journalist.setRoles(new HashSet<>());
        journalist.setUsername("janedoe");
        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        //WHEN
        when(journalist.getPassword()).thenReturn("iloveyou");
        when(journalist.getUsername()).thenReturn("janedoe");
        when(journalist.getRoles()).thenReturn(roleSet);
        doNothing().when(journalist).setCountry((String) any());
        doNothing().when(journalist).setEmail((String) any());
        doNothing().when(journalist).setId((Long) any());
        doNothing().when(journalist).setPassword((String) any());
        doNothing().when(journalist).setRoles((Set<Role>) any());
        doNothing().when(journalist).setUsername((String) any());
        when(this.userRepo.findByUsername((String) any())).thenReturn(journalist);

        //THEN
        UserDetails actualLoadUserByUsernameResult = this.userServiceImpl.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(this.userRepo).findByUsername((String) any());
        verify(journalist).getPassword();
        verify(journalist).getUsername();
        verify(journalist).getRoles();
        verify(journalist).setCountry((String) any());
        verify(journalist).setEmail((String) any());
        verify(journalist).setId((Long) any());
        verify(journalist).setPassword((String) any());
        verify(journalist).setRoles((Set<Role>) any());
        verify(journalist).setUsername((String) any());
    }

    @Test
    void testGetAllUsers() {
        //WHEN
        when(this.userRepo.findAll()).thenReturn(new ArrayList<>());
        //THEN
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
        verify(this.userRepo).findAll();
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllUsers2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   mindswap.academy.app.exceptions.UserNotFoundException: User not found with id 42
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at mindswap.academy.app.service.UserServiceImpl.getAllUsers(UserServiceImpl.java:62)
        //   In order to prevent getAllUsers()
        //   from throwing UserNotFoundException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAllUsers().
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setCountry("GB");
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsername("janedoe");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(this.userRepo.findAll()).thenReturn(userList);
        when(this.userConverter.toDto((User) any())).thenThrow(new UserNotFoundException("42"));
        this.userServiceImpl.getAllUsers();
    }

    @Test
    void testGetUserById() {
        //GIVEN
        User user = new User();
        user.setCountry("GB");
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        //WHEN
        when(this.userRepo.findById((Long) any())).thenReturn(ofResult);
        UserDto userDto = new UserDto("janedoe", "jane.doe@example.org", "GB", "Type");
        when(this.userConverter.toDto((User) any())).thenReturn(userDto);
        //THEN
        assertSame(userDto, this.userServiceImpl.getUserById(123L));
        verify(this.userRepo).findById((Long) any());
        verify(this.userConverter).toDto((User) any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   mindswap.academy.app.exceptions.UserNotFoundException: User not found with id 42
        //       at java.util.Optional.map(Optional.java:260)
        //       at mindswap.academy.app.service.UserServiceImpl.getUserById(UserServiceImpl.java:73)
        //   In order to prevent getUserById(Long)
        //   from throwing UserNotFoundException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getUserById(Long).
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setCountry("GB");
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepo.findById((Long) any())).thenReturn(ofResult);
        when(this.userConverter.toDto((User) any())).thenThrow(new UserNotFoundException("42"));
        this.userServiceImpl.getUserById(123L);
    }

    @Test
    void testGetUserById3() {
        when(this.userRepo.findById((Long) any())).thenReturn(Optional.empty());
        when(this.userConverter.toDto((User) any()))
                .thenReturn(new UserDto("janedoe", "jane.doe@example.org", "GB", "Type"));
        assertThrows(UserNotFoundException.class, () -> this.userServiceImpl.getUserById(123L));
        verify(this.userRepo).findById((Long) any());
    }

    @Test
    void testGetUserById4() {
        //GIVEN
        User user = new User();
        user.setCountry("GB");
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        //WHEN
        when(this.userRepo.findById((Long) any())).thenReturn(ofResult);
        when(this.userConverter.toDto((User) any()))
                .thenReturn(new UserDto("janedoe", "jane.doe@example.org", "GB", "Type"));
        //THEN
        assertThrows(InvalidRequestException.class, () -> this.userServiceImpl.getUserById(0L));
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGetCurrentUser() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getName()" because the return value of "org.springframework.security.core.context.SecurityContext.getAuthentication()" is null
        //       at mindswap.academy.app.service.UserServiceImpl.getCurrentUser(UserServiceImpl.java:80)
        //   In order to prevent getCurrentUser()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getCurrentUser().
        //   See https://diff.blue/R013 to resolve this issue.

        this.userServiceImpl.getCurrentUser();
    }
}*/

