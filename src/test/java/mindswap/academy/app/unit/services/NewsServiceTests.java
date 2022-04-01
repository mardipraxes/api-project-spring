package mindswap.academy.app.unit.services;

import mindswap.academy.app.MockData;
import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.commands.RegistrationDto;
import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.converters.NewsConverter;
import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.persistance.model.Journalist;
import mindswap.academy.app.persistance.model.Role;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.*;
import mindswap.academy.app.service.NewsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewsServiceTests {
    @Mock
    private NewsRepo newsRepo;
    @Mock
    private NewsConverter newsConverter;
    @Mock
    private ExternalNewsRepo externalNewsRepo;
    @Mock
    private UserRepo userRepo;
    @Mock
    private RatingTrackerRepo ratingTrackerRepo;

    private NewsServiceImpl newsServiceTest;

    @BeforeEach
    public void init() {
        newsServiceTest = new NewsServiceImpl(newsRepo, newsConverter, externalNewsRepo, userRepo, ratingTrackerRepo);
    }

    @Test
    public void testSearchNews() {
        //Given

        String[] categories = {"test"};
        String[] authors = {"test"};

        when(newsRepo.findByCategories(any())).thenReturn(List.of(MockData.getMockNewsPost()));

        //When
        List<NewsPostDto> newsPostDtoList = newsServiceTest.findNews(categories, authors);

        //Then
        assertEquals(1, newsPostDtoList.size());

    }







}
