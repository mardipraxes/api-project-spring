package mindswap.academy.app.converters;

import lombok.RequiredArgsConstructor;
import mindswap.academy.app.commands.NewsFindDto;
import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.persistance.model.*;
import mindswap.academy.app.persistance.repository.CategoryRepo;
import mindswap.academy.app.persistance.repository.RatingRepo;
import mindswap.academy.app.persistance.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NewsConverter {

    private final UserRepo userRepo;

    private final CategoryRepo categoryRepo;

    private final RatingRepo ratingRepo;



    public NewsPostDto toDtoFromExternalNews(ExternalNews externalNews) {

        NewsPostDto newsPostDto = NewsPostDto
                .builder()
                .title(externalNews.getTitle())
                .content(externalNews.getDescription())
                .date(externalNews.getPublishedAt())
                .imageURL(externalNews.getUrlToImage())
                .build();

        newsPostDto.setCategories(new String[]{externalNews.getCategory().getName()});

        return newsPostDto;
    }

    public NewsPost toEntity(NewsPostDto newsPostDto){

        String titleURL = newsPostDto.getTitle().replaceAll(" ", "-").toLowerCase().trim();

        NewsPost newsPost = NewsPost
                .builder()
                .title(newsPostDto.getTitle())
                .content(newsPostDto.getContent())
                .categories(new ArrayList<>())
                .publishedDate(new Date())
                .titleURL(titleURL)
                .imageURL(newsPostDto.getImageURL())
                .build();

        Arrays.stream(newsPostDto.getCategories())
                .filter(category -> categoryRepo.findByName(category) == null)
                .forEach(category -> categoryRepo.save(Category
                        .builder()
                        .name(category)
                        .description(category)
                        .build()));

        Arrays.stream(newsPostDto.getCategories())
                .forEach(category -> newsPost.getCategories().add(categoryRepo.findByName(category)));

        newsPost.setJournalist((Journalist) userRepo.findByUsername(newsPostDto.getAuthor()));
        Rating rating = Rating.builder()
                .biasedRating(0)
                .writingQuality(0)
                .truthfulness(0)
                .counter(0)
                .news(newsPost)
                .build();

        newsPost.setRating(rating);

        return newsPost;
    }

    public NewsPostDto toDto(NewsPost newsPost) {

        NewsPostDto newsPostDto = NewsPostDto
                .builder()
                .title(newsPost.getTitle())
                .content(newsPost.getContent())
                .imageURL(newsPost.getImageURL())
                .author(newsPost.getJournalist().getUsername())
                .build();

        newsPostDto.setCategories(newsPost.getCategories().stream().map(Category::getName).toArray(String[]::new));

        return newsPostDto;
    }

    public ExternalNews toExternalNewsEntity(NewsFindDto newsFindDto) {

        String titleURL = newsFindDto.getTitle().replaceAll(" ", "-").toLowerCase().trim();

        ExternalNews externalNews = ExternalNews.builder()
                .title(newsFindDto.getTitle())
                .description(newsFindDto.getDescription())
                .author(newsFindDto.getAuthor())
                .country(newsFindDto.getCountry())
                .titleURL(titleURL)
                .language(newsFindDto.getLanguage())
                .urlToImage(newsFindDto.getImage())
                .publishedAt(newsFindDto.getPublishedAt())
                .build();

        Category category = categoryRepo.findByName(newsFindDto.getCategory());

        if (category == null) {
            category = Category.builder()
                    .name(newsFindDto.getCategory())
                    .description(newsFindDto.getCategory())
                    .build();

            categoryRepo.save(category);
        }

        externalNews.setCategory(categoryRepo.findByName(newsFindDto.getCategory()));

        Rating rating = Rating.builder()
                .biasedRating(0)
                .writingQuality(0)
                .truthfulness(0)
                .counter(0)
                .externalNews(externalNews)
                .build();

        externalNews.setRating(rating);

        return externalNews;
    }
}
