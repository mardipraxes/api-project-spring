package mindswap.academy.app.integration.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.commands.RatingDto;
import mindswap.academy.app.converters.NewsConverter;
import mindswap.academy.app.exceptions.InvalidQueryException;
import mindswap.academy.app.exceptions.NewsPostAlreadyExistsException;
import mindswap.academy.app.persistance.model.Category;
import mindswap.academy.app.persistance.model.ExternalNews;
import mindswap.academy.app.persistance.model.Journalist;
import mindswap.academy.app.persistance.model.NewsPost;
import mindswap.academy.app.persistance.model.Rating;
import mindswap.academy.app.persistance.repository.NewsRepo;
import mindswap.academy.app.service.NewsServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NewsServiceImpl.class})
@ExtendWith(SpringExtension.class)

class NewsServiceImplTest {
    @MockBean
    private NewsConverter newsConverter;

    @MockBean
    private NewsRepo newsRepo;

    @Autowired
    private NewsServiceImpl newsServiceImpl;

    @Test
    void testPostNews() {
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

        ExternalNews externalNews = new ExternalNews();
        externalNews.setAuthor("JaneDoe");
        externalNews.setCategory(new Category());
        externalNews.setCountry("GB");
        externalNews.setDescription("The characteristics of someone or something");
        externalNews.setId(123L);
        externalNews.setLanguage("en");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        externalNews.setPublishedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        externalNews.setRating(new Rating());
        externalNews.setTitle("Dr");
        externalNews.setUrl("https://example.org/example");
        externalNews.setUrlToImage("https://example.org/example");

        NewsPost newsPost = new NewsPost();
        newsPost.setCategories(new ArrayList<>());
        newsPost.setContent("Not all who wander are lost");
        newsPost.setId(123L);
        newsPost.setImageURL("https://example.org/example");
        newsPost.setJournalist(new Journalist());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost.setPublishedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost.setRating(new Rating());
        newsPost.setTitle("Dr");

        Rating rating = new Rating();
        rating.setBiasedRating(1);
        rating.setCounter(3);
        rating.setExternalNews(externalNews);
        rating.setId(123L);
        rating.setNews(newsPost);
        rating.setTruthfulness(1);
        rating.setWritingQuality(1);

        ExternalNews externalNews1 = new ExternalNews();
        externalNews1.setAuthor("JaneDoe");
        externalNews1.setCategory(category);
        externalNews1.setCountry("GB");
        externalNews1.setDescription("The characteristics of someone or something");
        externalNews1.setId(123L);
        externalNews1.setLanguage("en");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        externalNews1.setPublishedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        externalNews1.setRating(rating);
        externalNews1.setTitle("Dr");
        externalNews1.setUrl("https://example.org/example");
        externalNews1.setUrlToImage("https://example.org/example");

        Journalist journalist1 = new Journalist();
        journalist1.setCountry("GB");
        journalist1.setEmail("jane.doe@example.org");
        journalist1.setId(123L);
        journalist1.setNewsPosts(new ArrayList<>());
        journalist1.setPassword("iloveyou");
        journalist1.setRoles(new HashSet<>());
        journalist1.setUsername("janedoe");

        ExternalNews externalNews2 = new ExternalNews();
        externalNews2.setAuthor("JaneDoe");
        externalNews2.setCategory(new Category());
        externalNews2.setCountry("GB");
        externalNews2.setDescription("The characteristics of someone or something");
        externalNews2.setId(123L);
        externalNews2.setLanguage("en");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        externalNews2.setPublishedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        externalNews2.setRating(new Rating());
        externalNews2.setTitle("Dr");
        externalNews2.setUrl("https://example.org/example");
        externalNews2.setUrlToImage("https://example.org/example");

        NewsPost newsPost1 = new NewsPost();
        newsPost1.setCategories(new ArrayList<>());
        newsPost1.setContent("Not all who wander are lost");
        newsPost1.setId(123L);
        newsPost1.setImageURL("https://example.org/example");
        newsPost1.setJournalist(new Journalist());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost1.setPublishedDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost1.setRating(new Rating());
        newsPost1.setTitle("Dr");

        Rating rating1 = new Rating();
        rating1.setBiasedRating(1);
        rating1.setCounter(3);
        rating1.setExternalNews(externalNews2);
        rating1.setId(123L);
        rating1.setNews(newsPost1);
        rating1.setTruthfulness(1);
        rating1.setWritingQuality(1);

        NewsPost newsPost2 = new NewsPost();
        newsPost2.setCategories(new ArrayList<>());
        newsPost2.setContent("Not all who wander are lost");
        newsPost2.setId(123L);
        newsPost2.setImageURL("https://example.org/example");
        newsPost2.setJournalist(journalist1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost2.setPublishedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost2.setRating(rating1);
        newsPost2.setTitle("Dr");

        Rating rating2 = new Rating();
        rating2.setBiasedRating(1);
        rating2.setCounter(3);
        rating2.setExternalNews(externalNews1);
        rating2.setId(123L);
        rating2.setNews(newsPost2);
        rating2.setTruthfulness(1);
        rating2.setWritingQuality(1);

        NewsPost newsPost3 = new NewsPost();
        newsPost3.setCategories(new ArrayList<>());
        newsPost3.setContent("Not all who wander are lost");
        newsPost3.setId(123L);
        newsPost3.setImageURL("https://example.org/example");
        newsPost3.setJournalist(journalist);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost3.setPublishedDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost3.setRating(rating2);
        newsPost3.setTitle("Dr");

        Journalist journalist2 = new Journalist();
        journalist2.setCountry("GB");
        journalist2.setEmail("jane.doe@example.org");
        journalist2.setId(123L);
        journalist2.setNewsPosts(new ArrayList<>());
        journalist2.setPassword("iloveyou");
        journalist2.setRoles(new HashSet<>());
        journalist2.setUsername("janedoe");

        Category category1 = new Category();
        category1.setDescription("The characteristics of someone or something");
        category1.setExternalNews(new ArrayList<>());
        category1.setId(123L);
        category1.setName("Name");
        category1.setNewsPost(new ArrayList<>());

        Rating rating3 = new Rating();
        rating3.setBiasedRating(1);
        rating3.setCounter(3);
        rating3.setExternalNews(new ExternalNews());
        rating3.setId(123L);
        rating3.setNews(new NewsPost());
        rating3.setTruthfulness(1);
        rating3.setWritingQuality(1);

        ExternalNews externalNews3 = new ExternalNews();
        externalNews3.setAuthor("JaneDoe");
        externalNews3.setCategory(category1);
        externalNews3.setCountry("GB");
        externalNews3.setDescription("The characteristics of someone or something");
        externalNews3.setId(123L);
        externalNews3.setLanguage("en");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        externalNews3.setPublishedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        externalNews3.setRating(rating3);
        externalNews3.setTitle("Dr");
        externalNews3.setUrl("https://example.org/example");
        externalNews3.setUrlToImage("https://example.org/example");

        Journalist journalist3 = new Journalist();
        journalist3.setCountry("GB");
        journalist3.setEmail("jane.doe@example.org");
        journalist3.setId(123L);
        journalist3.setNewsPosts(new ArrayList<>());
        journalist3.setPassword("iloveyou");
        journalist3.setRoles(new HashSet<>());
        journalist3.setUsername("janedoe");

        Rating rating4 = new Rating();
        rating4.setBiasedRating(1);
        rating4.setCounter(3);
        rating4.setExternalNews(new ExternalNews());
        rating4.setId(123L);
        rating4.setNews(new NewsPost());
        rating4.setTruthfulness(1);
        rating4.setWritingQuality(1);

        NewsPost newsPost4 = new NewsPost();
        newsPost4.setCategories(new ArrayList<>());
        newsPost4.setContent("Not all who wander are lost");
        newsPost4.setId(123L);
        newsPost4.setImageURL("https://example.org/example");
        newsPost4.setJournalist(journalist3);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost4.setPublishedDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost4.setRating(rating4);
        newsPost4.setTitle("Dr");

        Rating rating5 = new Rating();
        rating5.setBiasedRating(1);
        rating5.setCounter(3);
        rating5.setExternalNews(externalNews3);
        rating5.setId(123L);
        rating5.setNews(newsPost4);
        rating5.setTruthfulness(1);
        rating5.setWritingQuality(1);

        NewsPost newsPost5 = new NewsPost();
        newsPost5.setCategories(new ArrayList<>());
        newsPost5.setContent("Not all who wander are lost");
        newsPost5.setId(123L);
        newsPost5.setImageURL("https://example.org/example");
        newsPost5.setJournalist(journalist2);
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost5.setPublishedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost5.setRating(rating5);
        newsPost5.setTitle("Dr");
        Optional<NewsPost> ofResult = Optional.of(newsPost5);
        when(this.newsRepo.save((NewsPost) any())).thenReturn(newsPost3);
        when(this.newsRepo.findByTitle((String) any())).thenReturn(ofResult);
        assertThrows(NewsPostAlreadyExistsException.class, () -> this.newsServiceImpl.postNews(new NewsPostDto()));
        verify(this.newsRepo).findByTitle((String) any());
    }

    @Test
    void testFindNews() {
        assertThrows(InvalidQueryException.class,
                () -> this.newsServiceImpl.findNews(new String[]{"Categories"}, new String[]{"JaneDoe"}));
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testFindNews2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot read the array length because "array" is null
        //       at java.util.Arrays.stream(Arrays.java:5428)
        //       at mindswap.academy.app.service.NewsServiceImpl.findNews(NewsServiceImpl.java:46)
        //   In order to prevent findNews(String[], String[])
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   findNews(String[], String[]).
        //   See https://diff.blue/R013 to resolve this issue.

        this.newsServiceImpl.findNews(null, new String[]{"JaneDoe"});
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testRateNews() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because the return value of "mindswap.academy.app.commands.RatingDto.getBiasedRating()" is null
        //       at mindswap.academy.app.service.NewsServiceImpl.rateNews(NewsServiceImpl.java:90)
        //   In order to prevent rateNews(String, RatingDto)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   rateNews(String, RatingDto).
        //   See https://diff.blue/R013 to resolve this issue.

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
        Optional<NewsPost> ofResult = Optional.of(newsPost1);
        when(this.newsRepo.findByTitle((String) any())).thenReturn(ofResult);
        this.newsServiceImpl.rateNews("Dr", new RatingDto());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testRateNews2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because the return value of "mindswap.academy.app.commands.RatingDto.getBiasedRating()" is null
        //       at mindswap.academy.app.service.NewsServiceImpl.rateNews(NewsServiceImpl.java:90)
        //   In order to prevent rateNews(String, RatingDto)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   rateNews(String, RatingDto).
        //   See https://diff.blue/R013 to resolve this issue.

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

        Category category2 = new Category();
        category2.setDescription("The characteristics of someone or something");
        category2.setExternalNews(new ArrayList<>());
        category2.setId(123L);
        category2.setName("Name");
        category2.setNewsPost(new ArrayList<>());

        Rating rating3 = new Rating();
        rating3.setBiasedRating(1);
        rating3.setCounter(3);
        rating3.setExternalNews(new ExternalNews());
        rating3.setId(123L);
        rating3.setNews(new NewsPost());
        rating3.setTruthfulness(1);
        rating3.setWritingQuality(1);

        ExternalNews externalNews1 = new ExternalNews();
        externalNews1.setAuthor("JaneDoe");
        externalNews1.setCategory(category2);
        externalNews1.setCountry("GB");
        externalNews1.setDescription("The characteristics of someone or something");
        externalNews1.setId(123L);
        externalNews1.setLanguage("en");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        externalNews1.setPublishedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        externalNews1.setRating(rating3);
        externalNews1.setTitle("Dr");
        externalNews1.setUrl("https://example.org/example");
        externalNews1.setUrlToImage("https://example.org/example");

        Journalist journalist2 = new Journalist();
        journalist2.setCountry("GB");
        journalist2.setEmail("jane.doe@example.org");
        journalist2.setId(123L);
        journalist2.setNewsPosts(new ArrayList<>());
        journalist2.setPassword("iloveyou");
        journalist2.setRoles(new HashSet<>());
        journalist2.setUsername("janedoe");

        Rating rating4 = new Rating();
        rating4.setBiasedRating(1);
        rating4.setCounter(3);
        rating4.setExternalNews(new ExternalNews());
        rating4.setId(123L);
        rating4.setNews(new NewsPost());
        rating4.setTruthfulness(1);
        rating4.setWritingQuality(1);

        NewsPost newsPost1 = new NewsPost();
        newsPost1.setCategories(new ArrayList<>());
        newsPost1.setContent("Not all who wander are lost");
        newsPost1.setId(123L);
        newsPost1.setImageURL("https://example.org/example");
        newsPost1.setJournalist(journalist2);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost1.setPublishedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost1.setRating(rating4);
        newsPost1.setTitle("Dr");

        Rating rating5 = new Rating();
        rating5.setBiasedRating(1);
        rating5.setCounter(3);
        rating5.setExternalNews(externalNews1);
        rating5.setId(123L);
        rating5.setNews(newsPost1);
        rating5.setTruthfulness(1);
        rating5.setWritingQuality(1);

        ExternalNews externalNews2 = new ExternalNews();
        externalNews2.setAuthor("JaneDoe");
        externalNews2.setCategory(category1);
        externalNews2.setCountry("GB");
        externalNews2.setDescription("The characteristics of someone or something");
        externalNews2.setId(123L);
        externalNews2.setLanguage("en");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        externalNews2.setPublishedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        externalNews2.setRating(rating5);
        externalNews2.setTitle("Dr");
        externalNews2.setUrl("https://example.org/example");
        externalNews2.setUrlToImage("https://example.org/example");

        Journalist journalist3 = new Journalist();
        journalist3.setCountry("GB");
        journalist3.setEmail("jane.doe@example.org");
        journalist3.setId(123L);
        journalist3.setNewsPosts(new ArrayList<>());
        journalist3.setPassword("iloveyou");
        journalist3.setRoles(new HashSet<>());
        journalist3.setUsername("janedoe");

        Category category3 = new Category();
        category3.setDescription("The characteristics of someone or something");
        category3.setExternalNews(new ArrayList<>());
        category3.setId(123L);
        category3.setName("Name");
        category3.setNewsPost(new ArrayList<>());

        Rating rating6 = new Rating();
        rating6.setBiasedRating(1);
        rating6.setCounter(3);
        rating6.setExternalNews(new ExternalNews());
        rating6.setId(123L);
        rating6.setNews(new NewsPost());
        rating6.setTruthfulness(1);
        rating6.setWritingQuality(1);

        ExternalNews externalNews3 = new ExternalNews();
        externalNews3.setAuthor("JaneDoe");
        externalNews3.setCategory(category3);
        externalNews3.setCountry("GB");
        externalNews3.setDescription("The characteristics of someone or something");
        externalNews3.setId(123L);
        externalNews3.setLanguage("en");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        externalNews3.setPublishedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        externalNews3.setRating(rating6);
        externalNews3.setTitle("Dr");
        externalNews3.setUrl("https://example.org/example");
        externalNews3.setUrlToImage("https://example.org/example");

        Journalist journalist4 = new Journalist();
        journalist4.setCountry("GB");
        journalist4.setEmail("jane.doe@example.org");
        journalist4.setId(123L);
        journalist4.setNewsPosts(new ArrayList<>());
        journalist4.setPassword("iloveyou");
        journalist4.setRoles(new HashSet<>());
        journalist4.setUsername("janedoe");

        Rating rating7 = new Rating();
        rating7.setBiasedRating(1);
        rating7.setCounter(3);
        rating7.setExternalNews(new ExternalNews());
        rating7.setId(123L);
        rating7.setNews(new NewsPost());
        rating7.setTruthfulness(1);
        rating7.setWritingQuality(1);

        NewsPost newsPost2 = new NewsPost();
        newsPost2.setCategories(new ArrayList<>());
        newsPost2.setContent("Not all who wander are lost");
        newsPost2.setId(123L);
        newsPost2.setImageURL("https://example.org/example");
        newsPost2.setJournalist(journalist4);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost2.setPublishedDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost2.setRating(rating7);
        newsPost2.setTitle("Dr");

        Rating rating8 = new Rating();
        rating8.setBiasedRating(1);
        rating8.setCounter(3);
        rating8.setExternalNews(externalNews3);
        rating8.setId(123L);
        rating8.setNews(newsPost2);
        rating8.setTruthfulness(1);
        rating8.setWritingQuality(1);

        NewsPost newsPost3 = new NewsPost();
        newsPost3.setCategories(new ArrayList<>());
        newsPost3.setContent("Not all who wander are lost");
        newsPost3.setId(123L);
        newsPost3.setImageURL("https://example.org/example");
        newsPost3.setJournalist(journalist3);
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost3.setPublishedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost3.setRating(rating8);
        newsPost3.setTitle("Dr");

        Rating rating9 = new Rating();
        rating9.setBiasedRating(1);
        rating9.setCounter(3);
        rating9.setExternalNews(externalNews2);
        rating9.setId(123L);
        rating9.setNews(newsPost3);
        rating9.setTruthfulness(1);
        rating9.setWritingQuality(1);
        NewsPost newsPost4 = mock(NewsPost.class);
        when(newsPost4.getRating()).thenReturn(rating9);
        doNothing().when(newsPost4).setCategories((java.util.Collection<Category>) any());
        doNothing().when(newsPost4).setContent((String) any());
        doNothing().when(newsPost4).setId((Long) any());
        doNothing().when(newsPost4).setImageURL((String) any());
        doNothing().when(newsPost4).setJournalist((Journalist) any());
        doNothing().when(newsPost4).setPublishedDate((Date) any());
        doNothing().when(newsPost4).setRating((Rating) any());
        doNothing().when(newsPost4).setTitle((String) any());
        newsPost4.setCategories(new ArrayList<>());
        newsPost4.setContent("Not all who wander are lost");
        newsPost4.setId(123L);
        newsPost4.setImageURL("https://example.org/example");
        newsPost4.setJournalist(journalist);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        newsPost4.setPublishedDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        newsPost4.setRating(rating2);
        newsPost4.setTitle("Dr");
        Optional<NewsPost> ofResult = Optional.of(newsPost4);
        when(this.newsRepo.findByTitle((String) any())).thenReturn(ofResult);
        this.newsServiceImpl.rateNews("Dr", new RatingDto());
    }
}

