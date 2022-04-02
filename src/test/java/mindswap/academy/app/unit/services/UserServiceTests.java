package mindswap.academy.app.unit.services;

import mindswap.academy.app.MockData;
import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.commands.PasswordDto;
import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.converters.NewsConverter;
import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.exceptions.UserNotFoundException;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.ExternalNewsRepo;
import mindswap.academy.app.persistance.repository.NewsRepo;
import mindswap.academy.app.persistance.repository.RatingTrackerRepo;
import mindswap.academy.app.persistance.repository.UserRepo;
import mindswap.academy.app.service.NewsServiceImpl;
import mindswap.academy.app.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepo userRepo;
    @Mock
    private UserConverter userConverter;
    @Mock
    private SecurityContextHolder securityContextHolder;

    @Mock
    private Authentication authentication;


    private UserServiceImpl userServiceTests;

    @BeforeEach
    public void init() {
        this.userServiceTests = new UserServiceImpl(userRepo, userConverter);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void testGetAllUsers() {

        //Given
        List<User> userList = List.of(MockData.getMockUser());

        when(userRepo.findAll()).thenReturn(userList);
        when(userConverter.toDto(any())).thenReturn(MockData.getMockUserDto());

        //When
        List<UserDto> userDtoListReturned = userServiceTests.getAllUsers();

        //Then
        assertEquals(userDtoListReturned.size(), userList.size());
        assertEquals(userDtoListReturned.get(0).getUsername(), userList.get(0).getUsername());
    }

    @Test
    public void testGetUserById() {

        //Given
        Long id = 1L;
        User user = MockData.getMockUser();
        UserDto userDto = MockData.getMockUserDto();
        when(userRepo.findById(id)).thenReturn(Optional.of(user));
        when(userConverter.toDto(user)).thenReturn(userDto);
        //When
        UserDto userDtoReturned = userServiceTests.getUserById(id);
        //Then
        assertEquals(userDto.getUsername(), userDtoReturned.getUsername());
        assertEquals(user.getUsername(), userDtoReturned.getUsername());
    }
    @Test
    public void testGetUserByIdButUserIsNotFound() {

        //Given
        Long id = 1L;
        User user = MockData.getMockUser();
        UserDto userDto = MockData.getMockUserDto();
        when(userRepo.findById(id)).thenReturn(Optional.empty());
        //When
        //Then
        assertThrows(UserNotFoundException.class, () -> userServiceTests.getUserById(id));
    }

    @Test
    public void testGetUserByUsername() {
        //Given
        String username = "joao";
        User user = MockData.getMockUser();
        UserDto userDto = MockData.getMockUserDto();
        //WHEN
        when(userRepo.findByUsername(username)).thenReturn(user);
        User userDtoReturned = userServiceTests.getUserByUsername(username);
        //THEN
        assertEquals(userDto.getUsername(), userDtoReturned.getUsername());
        assertEquals(user.getUsername(), userDtoReturned.getUsername());
    }
    @Test
    public void getCurrentUser() {
        //Given
        String username = "joao";
        User user = MockData.getMockUser();
        UserDto userDto = MockData.getMockUserDto();
        PasswordDto passwordDto = MockData.getMockPasswordDto();
        //WHEN
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("joao");
        when(userRepo.findByUsername(username)).thenReturn(user);
        when(userConverter.toDto(user)).thenReturn(userDto);
        //THEN
        UserDto userDtoReturned = userServiceTests.getCurrentUser();
        assertEquals(userDto.getUsername(), userDtoReturned.getUsername());
        assertEquals(user.getUsername(), userDtoReturned.getUsername());

    }



}
