package mindswap.academy.app.unit.services;

import mindswap.academy.app.MockData;
import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.commands.RatingDto;
import mindswap.academy.app.commands.RegistrationDto;
import mindswap.academy.app.commands.UserDto;
import mindswap.academy.app.converters.NewsConverter;
import mindswap.academy.app.converters.UserConverter;
import mindswap.academy.app.exceptions.InvalidQueryException;
import mindswap.academy.app.exceptions.NewsPostAlreadyExistsException;
import mindswap.academy.app.persistance.model.*;
import mindswap.academy.app.persistance.repository.*;
import mindswap.academy.app.service.NewsServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private Authentication authentication;

    private NewsServiceImpl newsServiceTest;

    @BeforeEach
    public void init() {
        newsServiceTest =
                new NewsServiceImpl(newsRepo, newsConverter, externalNewsRepo, userRepo, ratingTrackerRepo, categoryRepo);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void testSearchNews() {
        //Given

        String[] categories = {"test"};
        String[] authors = {"test"};

        Category category = MockData.getMockCategory();
        category.setNewsPost(List.of(MockData.getMockNewsPost()));
        when(categoryRepo.findByName(any())).thenReturn(category);


        //When
        List<NewsPostDto> newsPostDtoList = newsServiceTest.findNews(categories, authors);

        //Then
        assertEquals(1, newsPostDtoList.size());

    }

    @Test
    public void testSearchNewsButParametersAreNull() {
        //Given

        String[] categories = null;
        String[] authors = null;


        //When
        //Then
        assertThrows(InvalidQueryException.class, () -> newsServiceTest.findNews(categories, authors));

    }

    @Test
    public void testSearchNewsButNoAuthorsMatchCategories() {
        //Given

        String[] categories = {"test"};
        String[] authors = {"test23"};
        Category category = MockData.getMockCategory();
        category.setNewsPost(List.of(MockData.getMockNewsPost()));

//        when(newsRepo.findByCategories(any())).thenReturn(List.of(MockData.getMockNewsPost()));
        when(categoryRepo.findByName(any())).thenReturn(category);

        //When
        //Then
        assertThrows(InvalidQueryException.class, () -> newsServiceTest.findNews(categories, authors));

    }

    @Test
    public void testSearchNewsButAuthorsIsEmpty() {
        //Given

        String[] categories = {"test"};
        String[] authors = {"[]"};

        Category category = MockData.getMockCategory();
        category.setNewsPost(List.of(MockData.getMockNewsPost()));

//        when(newsRepo.findByCategories(any())).thenReturn(List.of(MockData.getMockNewsPost()));
        when(categoryRepo.findByName(any())).thenReturn(category);

        //When
        List<NewsPostDto> newsPostDtoList = newsServiceTest.findNews(categories, authors);

        //Then
        assertEquals(1, newsPostDtoList.size());

    }

    @Test
    public void testPostNews() {
        //Given
        NewsPostDto newsPostDto = MockData.getMockNewsPostDto();
        when(newsRepo.findByTitle(any())).thenReturn(Optional.empty());
        when(newsConverter.toEntity(any())).thenReturn(MockData.getMockNewsPost());


        //When
        newsServiceTest.postNews(newsPostDto);

        //Then
        verify(newsRepo, times(1)).save(any());

    }

    @Test
    public void testPostNewsButTitleAlreadyExists() {
        //Given
        NewsPostDto newsPostDto = MockData.getMockNewsPostDto();
        when(newsRepo.findByTitle(any())).thenReturn(Optional.ofNullable(MockData.getMockNewsPost()));

        //When
        //Then
        assertThrows(NewsPostAlreadyExistsException.class, () -> newsServiceTest.postNews(newsPostDto));

    }

    @Test
    public void testRateNews() {
        //Given
        Rating rating = MockData.getMockRating();
        Rating rating2 = MockData.getMockRating();
        RatingDto ratingDto = MockData.getMockRatingDto();
        NewsPost newsPost = MockData.getMockNewsPost();
        User user = MockData.getMockUser();

        newsPost.setRating(rating);
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("test");
        when(userRepo.findByUsername(any())).thenReturn(user);
        when(newsRepo.findByTitleURL(any())).thenReturn(Optional.of(newsPost));
        when(ratingTrackerRepo.findByUserId(any())).thenReturn(null);

        //When
        newsServiceTest.rateNews(newsPost.getTitle(), ratingDto);

        //Then
        verify(newsRepo, times(1)).save(any());
        assertEquals(rating2.getBiasedRating() + 2, newsPost.getRating().getBiasedRating());
        assertEquals(rating2.getWritingQuality() + 2, newsPost.getRating().getWritingQuality());
        assertEquals(rating2.getTruthfulness() + 2, newsPost.getRating().getTruthfulness());
        assertEquals(rating2.getCounter() + 1, newsPost.getRating().getCounter());


    }
}
