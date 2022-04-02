package mindswap.academy.app.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import mindswap.academy.app.commands.NewsFindDto;

import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.persistance.model.Category;
import mindswap.academy.app.persistance.model.ExternalNews;
import mindswap.academy.app.persistance.model.Journalist;
import mindswap.academy.app.persistance.model.NewsPost;
import mindswap.academy.app.persistance.model.Rating;
import mindswap.academy.app.persistance.model.Role;
import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.CategoryRepo;
import mindswap.academy.app.persistance.repository.RatingRepo;
import mindswap.academy.app.persistance.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NewsConverter.class})
@ExtendWith(SpringExtension.class)
class NewsConverterTest {
    @MockBean
    private CategoryRepo categoryRepo;

    @Autowired
    private NewsConverter newsConverter;

    @MockBean
    private RatingRepo ratingRepo;

    @MockBean
    private UserRepo userRepo;

    @Test
    @Disabled("TODO: Complete this test")
    void testToEntity() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot read the array length because "array" is null
        //       at java.util.Arrays.stream(Arrays.java:5428)
        //       at mindswap.academy.app.converters.NewsConverter.toEntity(NewsConverter.java:40)
        //   In order to prevent toEntity(NewsPostDto)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   toEntity(NewsPostDto).
        //   See https://diff.blue/R013 to resolve this issue.

        this.newsConverter.toEntity(new NewsPostDto());
    }

    @Test
    void testToEntity2() {

        // GIVEN
        /*
        Journalist journalist = new Journalist();
        journalist.setCountry("GB");
        journalist.setEmail("jane.doe@example.org");
        journalist.setId(123L);
        journalist.setNewsPosts(new ArrayList<>());
        journalist.setPassword("iloveyou");
        journalist.setRoles(new HashSet<>());
        journalist.setUsername("janedoe");
        journalist.setCountry("GB");
        journalist.setEmail("jane.doe@example.org");
        journalist.setId(123L);
        journalist.setPassword("iloveyou");
        journalist.setRoles(new HashSet<>());
        journalist.setUsername("janedoe");
        when(this.userRepo.findByUsername((String) any())).thenReturn(journalist);

        Category category = new Category();
        category.setDescription("The characteristics of someone or something");
        category.setExternalNews(new ArrayList<>());
        category.setId(123L);
        category.setName("Name");
        category.setNewsPost(new ArrayList<>());

        Category category1 = new Category();
        category1.setDescription("The characteristics of someone or something");
        category1.setExternalNews(new ArrayList<>());
        category1.setId(123L);
        category1.setName("Name");
        category1.setNewsPost(new ArrayList<>());
        when(this.categoryRepo.save((Category) any())).thenReturn(category);
        when(this.categoryRepo.findByName((String) any())).thenReturn(category1);
        NewsPost actualToEntityResult = this.newsConverter.toEntity(new NewsPostDto("Dr", "Not all who wander are lost",
                "JaneDoe", new String[]{"Categories"}, "https://example.org/example"));
        assertEquals(1, actualToEntityResult.getCategories().size());
        assertEquals("Dr", actualToEntityResult.getTitle());
        assertEquals("Not all who wander are lost", actualToEntityResult.getContent());
        assertEquals("https://example.org/example", actualToEntityResult.getImageURL());
        assertSame(journalist, actualToEntityResult.getJournalist());
        assertNull(actualToEntityResult.getId());
        Rating rating = actualToEntityResult.getRating();
        assertNull(rating.getId());
        assertNull(rating.getExternalNews());
        assertEquals(0, rating.getCounter().intValue());
        assertEquals(0, rating.getBiasedRating().intValue());
        assertEquals(0, rating.getWritingQuality().intValue());
        assertSame(actualToEntityResult, rating.getNews());
        assertEquals(0, rating.getTruthfulness().intValue());
        verify(this.userRepo).findByUsername((String) any());
        verify(this.categoryRepo, atLeast(1)).findByName((String) any());
        */

    }

    @Test
    @Disabled("TODO: Complete this test")
    void testToEntity3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "mindswap.academy.app.commands.NewsPostDto.getTitle()" because "newsPostDto" is null
        //       at mindswap.academy.app.converters.NewsConverter.toEntity(NewsConverter.java:33)
        //   In order to prevent toEntity(NewsPostDto)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   toEntity(NewsPostDto).
        //   See https://diff.blue/R013 to resolve this issue.

        User user = mock(User.class);
        doNothing().when(user).setCountry((String) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setId((Long) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRoles((java.util.Set<Role>) any());
        doNothing().when(user).setUsername((String) any());
        user.setCountry("GB");
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUsername("janedoe");
        when(this.userRepo.findByUsername((String) any())).thenReturn(user);
        Category category = mock(Category.class);
        doNothing().when(category).setDescription((String) any());
        doNothing().when(category).setExternalNews((java.util.Collection<ExternalNews>) any());
        doNothing().when(category).setId((Long) any());
        doNothing().when(category).setName((String) any());
        doNothing().when(category).setNewsPost((java.util.Collection<NewsPost>) any());
        category.setDescription("The characteristics of someone or something");
        category.setExternalNews(new ArrayList<>());
        category.setId(123L);
        category.setName("Name");
        category.setNewsPost(new ArrayList<>());
        Category category1 = mock(Category.class);
        doNothing().when(category1).setDescription((String) any());
        doNothing().when(category1).setExternalNews((java.util.Collection<ExternalNews>) any());
        doNothing().when(category1).setId((Long) any());
        doNothing().when(category1).setName((String) any());
        doNothing().when(category1).setNewsPost((java.util.Collection<NewsPost>) any());
        category1.setDescription("The characteristics of someone or something");
        category1.setExternalNews(new ArrayList<>());
        category1.setId(123L);
        category1.setName("Name");
        category1.setNewsPost(new ArrayList<>());
        when(this.categoryRepo.save((Category) any())).thenReturn(category);
        when(this.categoryRepo.findByName((String) any())).thenReturn(category1);
        this.newsConverter.toEntity(null);
    }

    @Test
    void testToDto() {
        Journalist journalist = new Journalist();
        journalist.setCountry("GB");
        journalist.setEmail("jane.doe@example.org");
        journalist.setId(123L);
        journalist.setNewsPosts(new ArrayList<>());
        journalist.setPassword("iloveyou");
        journalist.setRoles(new HashSet<>());
        journalist.setUsername("janedoe");

        Category category = new Category();
        category.setDescription("The characteristics of someone or something");
        category.setExternalNews(new ArrayList<>());
        category.setId(123L);
        category.setName("Name");
        category.setNewsPost(new ArrayList<>());

        Rating rating = new Rating();
        rating.setBiasedRating(1);
        rating.setCounter(3);
        rating.setExternalNews(new ExternalNews());
        rating.setId(123L);
        rating.setNews(new NewsPost());
        rating.setTruthfulness(1);
        rating.setWritingQuality(1);

        ExternalNews externalNews = new ExternalNews();
        externalNews.setAuthor("JaneDoe");
        externalNews.setCategory(category);
        externalNews.setCountry("GB");
        externalNews.setDescription("The characteristics of someone or something");
        externalNews.setId(123L);
        externalNews.setLanguage("en");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        externalNews.setPublishedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        externalNews.setRating(rating);
        externalNews.setTitle("Dr");
        externalNews.setUrl("https://example.org/example");
        externalNews.setUrlToImage("https://example.org/example");

        Journalist journalist1 = new Journalist();
        journalist1.setCountry("GB");
        journalist1.setEmail("jane.doe@example.org");
        journalist1.setId(123L);
        journalist1.setNewsPosts(new ArrayList<>());
        journalist1.setPassword("iloveyou");
        journalist1.setRoles(new HashSet<>());
        journalist1.setUsername("janedoe");

        Rating rating1 = new Rating();
        rating1.setBiasedRating(1);
        rating1.setCounter(3);
        rating1.setExternalNews(new ExternalNews());
        rating1.setId(123L);
        rating1.setNews(new NewsPost());
        rating1.setTruthfulness(1);
        rating1.setWritingQuality(1);

        NewsPost newsPost = new NewsPost();
        newsPost.setCategories(new ArrayList<>());
        newsPost.setContent("Not all who wander are lost");
        newsPost.setId(123L);
        newsPost.setImageURL("https://example.org/example");
        newsPost.setJournalist(journalist1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost.setPublishedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost.setRating(rating1);
        newsPost.setTitle("Dr");

        Rating rating2 = new Rating();
        rating2.setBiasedRating(1);
        rating2.setCounter(3);
        rating2.setExternalNews(externalNews);
        rating2.setId(123L);
        rating2.setNews(newsPost);
        rating2.setTruthfulness(1);
        rating2.setWritingQuality(1);

        NewsPost newsPost1 = new NewsPost();
        newsPost1.setCategories(new ArrayList<>());
        newsPost1.setContent("Not all who wander are lost");
        newsPost1.setId(123L);
        newsPost1.setImageURL("https://example.org/example");
        newsPost1.setJournalist(journalist);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost1.setPublishedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost1.setRating(rating2);
        newsPost1.setTitle("Dr");
        NewsPostDto actualToDtoResult = this.newsConverter.toDto(newsPost1);
        assertNull(actualToDtoResult.getAuthor());
        assertEquals("Dr", actualToDtoResult.getTitle());
        assertEquals("https://example.org/example", actualToDtoResult.getImageURL());
        assertEquals("Not all who wander are lost", actualToDtoResult.getContent());
        assertEquals(0, actualToDtoResult.getCategories().length);
    }

    //Branch

    @Test
    void testToDto2() {
        Journalist journalist = new Journalist();
        journalist.setCountry("GB");
        journalist.setEmail("jane.doe@example.org");
        journalist.setId(123L);
        journalist.setNewsPosts(new ArrayList<>());
        journalist.setPassword("iloveyou");
        journalist.setRoles(new HashSet<>());
        journalist.setUsername("janedoe");

        Category category = new Category();
        category.setDescription("The characteristics of someone or something");
        category.setExternalNews(new ArrayList<>());
        category.setId(123L);
        category.setName("Name");
        category.setNewsPost(new ArrayList<>());

        Rating rating = new Rating();
        rating.setBiasedRating(1);
        rating.setCounter(3);
        rating.setExternalNews(new ExternalNews());
        rating.setId(123L);
        rating.setNews(new NewsPost());
        rating.setTruthfulness(1);
        rating.setWritingQuality(1);

        ExternalNews externalNews = new ExternalNews();
        externalNews.setAuthor("JaneDoe");
        externalNews.setCategory(category);
        externalNews.setCountry("GB");
        externalNews.setDescription("The characteristics of someone or something");
        externalNews.setId(123L);
        externalNews.setLanguage("en");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        externalNews.setPublishedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        externalNews.setRating(rating);
        externalNews.setTitle("Dr");
        externalNews.setUrl("https://example.org/example");
        externalNews.setUrlToImage("https://example.org/example");

        Journalist journalist1 = new Journalist();
        journalist1.setCountry("GB");
        journalist1.setEmail("jane.doe@example.org");
        journalist1.setId(123L);
        journalist1.setNewsPosts(new ArrayList<>());
        journalist1.setPassword("iloveyou");
        journalist1.setRoles(new HashSet<>());
        journalist1.setUsername("janedoe");

        Rating rating1 = new Rating();
        rating1.setBiasedRating(1);
        rating1.setCounter(3);
        rating1.setExternalNews(new ExternalNews());
        rating1.setId(123L);
        rating1.setNews(new NewsPost());
        rating1.setTruthfulness(1);
        rating1.setWritingQuality(1);

        NewsPost newsPost = new NewsPost();
        newsPost.setCategories(new ArrayList<>());
        newsPost.setContent("Not all who wander are lost");
        newsPost.setId(123L);
        newsPost.setImageURL("https://example.org/example");
        newsPost.setJournalist(journalist1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost.setPublishedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost.setRating(rating1);
        newsPost.setTitle("Dr");

        Rating rating2 = new Rating();
        rating2.setBiasedRating(1);
        rating2.setCounter(3);
        rating2.setExternalNews(externalNews);
        rating2.setId(123L);
        rating2.setNews(newsPost);
        rating2.setTruthfulness(1);
        rating2.setWritingQuality(1);
        NewsPost newsPost1 = mock(NewsPost.class);
        when(newsPost1.getContent()).thenReturn("Not all who wander are lost");
        when(newsPost1.getImageURL()).thenReturn("https://example.org/example");
        when(newsPost1.getTitle()).thenReturn("Dr");
        when(newsPost1.getCategories()).thenReturn(new ArrayList<>());
        doNothing().when(newsPost1).setCategories((Collection<Category>) any());
        doNothing().when(newsPost1).setContent((String) any());
        doNothing().when(newsPost1).setId((Long) any());
        doNothing().when(newsPost1).setImageURL((String) any());
        doNothing().when(newsPost1).setJournalist((Journalist) any());
        doNothing().when(newsPost1).setPublishedDate((Date) any());
        doNothing().when(newsPost1).setRating((Rating) any());
        doNothing().when(newsPost1).setTitle((String) any());
        newsPost1.setCategories(new ArrayList<>());
        newsPost1.setContent("Not all who wander are lost");
        newsPost1.setId(123L);
        newsPost1.setImageURL("https://example.org/example");
        newsPost1.setJournalist(journalist);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost1.setPublishedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost1.setRating(rating2);
        newsPost1.setTitle("Dr");
        NewsPostDto actualToDtoResult = this.newsConverter.toDto(newsPost1);
        assertNull(actualToDtoResult.getAuthor());
        assertEquals("Dr", actualToDtoResult.getTitle());
        assertEquals("https://example.org/example", actualToDtoResult.getImageURL());
        assertEquals("Not all who wander are lost", actualToDtoResult.getContent());
        assertEquals(0, actualToDtoResult.getCategories().length);
        verify(newsPost1).getContent();
        verify(newsPost1).getImageURL();
        verify(newsPost1).getTitle();
        verify(newsPost1).getCategories();
        verify(newsPost1).setCategories((Collection<Category>) any());
        verify(newsPost1).setContent((String) any());
        verify(newsPost1).setId((Long) any());
        verify(newsPost1).setImageURL((String) any());
        verify(newsPost1).setJournalist((Journalist) any());
        verify(newsPost1).setPublishedDate((Date) any());
        verify(newsPost1).setRating((Rating) any());
        verify(newsPost1).setTitle((String) any());
    }

    @Test
    void testToDto3() {
        Journalist journalist = new Journalist();
        journalist.setCountry("GB");
        journalist.setEmail("jane.doe@example.org");
        journalist.setId(123L);
        journalist.setNewsPosts(new ArrayList<>());
        journalist.setPassword("iloveyou");
        journalist.setRoles(new HashSet<>());
        journalist.setUsername("janedoe");

        Category category = new Category();
        category.setDescription("The characteristics of someone or something");
        category.setExternalNews(new ArrayList<>());
        category.setId(123L);
        category.setName("Name");
        category.setNewsPost(new ArrayList<>());

        Rating rating = new Rating();
        rating.setBiasedRating(1);
        rating.setCounter(3);
        rating.setExternalNews(new ExternalNews());
        rating.setId(123L);
        rating.setNews(new NewsPost());
        rating.setTruthfulness(1);
        rating.setWritingQuality(1);

        ExternalNews externalNews = new ExternalNews();
        externalNews.setAuthor("JaneDoe");
        externalNews.setCategory(category);
        externalNews.setCountry("GB");
        externalNews.setDescription("The characteristics of someone or something");
        externalNews.setId(123L);
        externalNews.setLanguage("en");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        externalNews.setPublishedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        externalNews.setRating(rating);
        externalNews.setTitle("Dr");
        externalNews.setUrl("https://example.org/example");
        externalNews.setUrlToImage("https://example.org/example");

        Journalist journalist1 = new Journalist();
        journalist1.setCountry("GB");
        journalist1.setEmail("jane.doe@example.org");
        journalist1.setId(123L);
        journalist1.setNewsPosts(new ArrayList<>());
        journalist1.setPassword("iloveyou");
        journalist1.setRoles(new HashSet<>());
        journalist1.setUsername("janedoe");

        Rating rating1 = new Rating();
        rating1.setBiasedRating(1);
        rating1.setCounter(3);
        rating1.setExternalNews(new ExternalNews());
        rating1.setId(123L);
        rating1.setNews(new NewsPost());
        rating1.setTruthfulness(1);
        rating1.setWritingQuality(1);

        NewsPost newsPost = new NewsPost();
        newsPost.setCategories(new ArrayList<>());
        newsPost.setContent("Not all who wander are lost");
        newsPost.setId(123L);
        newsPost.setImageURL("https://example.org/example");
        newsPost.setJournalist(journalist1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost.setPublishedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost.setRating(rating1);
        newsPost.setTitle("Dr");

        Rating rating2 = new Rating();
        rating2.setBiasedRating(1);
        rating2.setCounter(3);
        rating2.setExternalNews(externalNews);
        rating2.setId(123L);
        rating2.setNews(newsPost);
        rating2.setTruthfulness(1);
        rating2.setWritingQuality(1);

        Category category1 = new Category();
        category1.setDescription("The characteristics of someone or something");
        category1.setExternalNews(new ArrayList<>());
        category1.setId(123L);
        category1.setName("Name");
        category1.setNewsPost(new ArrayList<>());

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        NewsPost newsPost1 = mock(NewsPost.class);
        when(newsPost1.getContent()).thenReturn("Not all who wander are lost");
        when(newsPost1.getImageURL()).thenReturn("https://example.org/example");
        when(newsPost1.getTitle()).thenReturn("Dr");
        when(newsPost1.getCategories()).thenReturn(categoryList);
        doNothing().when(newsPost1).setCategories((Collection<Category>) any());
        doNothing().when(newsPost1).setContent((String) any());
        doNothing().when(newsPost1).setId((Long) any());
        doNothing().when(newsPost1).setImageURL((String) any());
        doNothing().when(newsPost1).setJournalist((Journalist) any());
        doNothing().when(newsPost1).setPublishedDate((Date) any());
        doNothing().when(newsPost1).setRating((Rating) any());
        doNothing().when(newsPost1).setTitle((String) any());
        newsPost1.setCategories(new ArrayList<>());
        newsPost1.setContent("Not all who wander are lost");
        newsPost1.setId(123L);
        newsPost1.setImageURL("https://example.org/example");
        newsPost1.setJournalist(journalist);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost1.setPublishedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost1.setRating(rating2);
        newsPost1.setTitle("Dr");
        NewsPostDto actualToDtoResult = this.newsConverter.toDto(newsPost1);
        assertNull(actualToDtoResult.getAuthor());
        assertEquals("Dr", actualToDtoResult.getTitle());
        assertEquals("https://example.org/example", actualToDtoResult.getImageURL());
        assertEquals("Not all who wander are lost", actualToDtoResult.getContent());
        assertEquals(1, actualToDtoResult.getCategories().length);
        verify(newsPost1).getContent();
        verify(newsPost1).getImageURL();
        verify(newsPost1).getTitle();
        verify(newsPost1).getCategories();
        verify(newsPost1).setCategories((Collection<Category>) any());
        verify(newsPost1).setContent((String) any());
        verify(newsPost1).setId((Long) any());
        verify(newsPost1).setImageURL((String) any());
        verify(newsPost1).setJournalist((Journalist) any());
        verify(newsPost1).setPublishedDate((Date) any());
        verify(newsPost1).setRating((Rating) any());
        verify(newsPost1).setTitle((String) any());
    }

    @Test
    void testToExternalNewsEntity() {
        Category category = new Category();
        category.setDescription("The characteristics of someone or something");
        category.setExternalNews(new ArrayList<>());
        category.setId(123L);
        category.setName("Name");
        category.setNewsPost(new ArrayList<>());

        Category category1 = new Category();
        category1.setDescription("The characteristics of someone or something");
        category1.setExternalNews(new ArrayList<>());
        category1.setId(123L);
        category1.setName("Name");
        category1.setNewsPost(new ArrayList<>());
        when(this.categoryRepo.save((Category) any())).thenReturn(category);
        when(this.categoryRepo.findByName((String) any())).thenReturn(category1);
        ExternalNews actualToExternalNewsEntityResult = this.newsConverter.toExternalNewsEntity(new NewsFindDto());
        assertNull(actualToExternalNewsEntityResult.getAuthor());
        assertNull(actualToExternalNewsEntityResult.getUrlToImage());
        assertNull(actualToExternalNewsEntityResult.getUrl());
        assertNull(actualToExternalNewsEntityResult.getTitle());
        assertNull(actualToExternalNewsEntityResult.getPublishedAt());
        assertNull(actualToExternalNewsEntityResult.getCategory());
        assertNull(actualToExternalNewsEntityResult.getDescription());
        assertNull(actualToExternalNewsEntityResult.getId());
        assertNull(actualToExternalNewsEntityResult.getCountry());
        assertNull(actualToExternalNewsEntityResult.getLanguage());
        Rating rating = actualToExternalNewsEntityResult.getRating();
        assertEquals(0, rating.getCounter().intValue());
        assertEquals(0, rating.getBiasedRating().intValue());
        assertNull(rating.getId());
        assertEquals(0, rating.getTruthfulness().intValue());
        assertEquals(0, rating.getWritingQuality().intValue());
        assertSame(actualToExternalNewsEntityResult, rating.getExternalNews());
        assertNull(rating.getNews());
        verify(this.categoryRepo).findByName((String) any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testToExternalNewsEntity2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "mindswap.academy.app.commands.NewsFindDto.getTitle()" because "newsFindDto" is null
        //       at mindswap.academy.app.converters.NewsConverter.toExternalNewsEntity(NewsConverter.java:82)
        //   In order to prevent toExternalNewsEntity(NewsFindDto)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   toExternalNewsEntity(NewsFindDto).
        //   See https://diff.blue/R013 to resolve this issue.

        Category category = new Category();
        category.setDescription("The characteristics of someone or something");
        category.setExternalNews(new ArrayList<>());
        category.setId(123L);
        category.setName("Name");
        category.setNewsPost(new ArrayList<>());

        Category category1 = new Category();
        category1.setDescription("The characteristics of someone or something");
        category1.setExternalNews(new ArrayList<>());
        category1.setId(123L);
        category1.setName("Name");
        category1.setNewsPost(new ArrayList<>());
        when(this.categoryRepo.save((Category) any())).thenReturn(category);
        when(this.categoryRepo.findByName((String) any())).thenReturn(category1);
        this.newsConverter.toExternalNewsEntity(null);
    }
}

