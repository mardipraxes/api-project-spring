package mindswap.academy.app.unit.converters;

import mindswap.academy.app.MockData;
import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.converters.NewsConverter;
import mindswap.academy.app.persistance.model.NewsPost;
import mindswap.academy.app.persistance.repository.CategoryRepo;
import mindswap.academy.app.persistance.repository.RatingRepo;
import mindswap.academy.app.persistance.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewsConverterTest {

    @Mock
    private RatingRepo ratingRepo;

    private NewsConverter newsConverter;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    public void setup() {
        this.newsConverter = new NewsConverter(userRepo, categoryRepo,ratingRepo);
    }

    @Test
    public void testFromNewsPostToDto() {

        //Given
        NewsPost newsPost = MockData.getMockNewsPost();

        //When
        NewsPostDto newsPostDto = newsConverter.toDto(newsPost);

        //Then
        assertEquals(newsPost.getTitle(), newsPostDto.getTitle());
        assertEquals(newsPost.getContent(), newsPostDto.getContent());

    }


}
