package mindswap.academy.app.unit.services;

import mindswap.academy.app.MockData;
import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.converters.NewsConverter;
import mindswap.academy.app.converters.UserConverter;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepo userRepo;
    @Mock
    private UserConverter userConverter;


    private UserServiceImpl userServiceTests;

    @BeforeEach
    public void init() {
        this.userServiceTests = new UserServiceImpl(userRepo, userConverter);
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







}
