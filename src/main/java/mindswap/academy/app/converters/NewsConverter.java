package mindswap.academy.app.converters;

import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.persistance.model.Category;
import mindswap.academy.app.persistance.model.Journalist;
import mindswap.academy.app.persistance.model.NewsPost;
import mindswap.academy.app.persistance.model.Rating;
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
public class NewsConverter {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private RatingRepo ratingRepo;

    public NewsPost toEntity(NewsPostDto newsPostDto){

        NewsPost newsPost = NewsPost
                .builder()
                .title(newsPostDto.getTitle())
                .content(newsPostDto.getContent())
                .categories(new ArrayList<>())
                .publishedDate(new Date())
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

        newsPost.setJournalist(userRepo.findByUsername(newsPostDto.getAuthor()));

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
}
