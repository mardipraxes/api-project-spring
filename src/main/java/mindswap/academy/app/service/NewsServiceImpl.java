package mindswap.academy.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.EditNewsDto;
import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.commands.RatingDto;
import mindswap.academy.app.converters.NewsConverter;
import mindswap.academy.app.exceptions.*;
import mindswap.academy.app.persistance.model.*;
import mindswap.academy.app.persistance.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {


    private final NewsRepo newsRepo;

    private final NewsConverter newsConverter;

    private final ExternalNewsRepo externalNewsRepo;

    private final UserRepo userRepo;

    private final RatingTrackerRepo ratingTrackerRepo;

    private final CategoryRepo categoryRepo;



    public void postNews(NewsPostDto newsPostDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userRepo.findByUsername(username) == null || !(userRepo.findByUsername(username) instanceof Journalist)){
            log.warn("User not found");
            log.info("User is using a fake account");
            throw new UserNotFoundException(username);
        }

        if (newsRepo.findByTitle(newsPostDto.getTitle()).isPresent()) {
            log.warn("News post already exists");
            throw new NewsPostAlreadyExistsException(newsPostDto.getTitle());
        }
        newsRepo.save(newsConverter.toEntity(newsPostDto));

    }

    @Override
    public List<NewsPostDto> findNews(String[] categories, String[] author) {

        List<NewsPost> newsPosts = new ArrayList<>();
        if (!verifyValidQuery(categories)) {
            log.warn("Invalid query");
            throw new InvalidQueryException();
        }

        Arrays.stream(categories)
                .map(categoryRepo::findByName)
                .forEach(category -> newsPosts.addAll(category.getNewsPost()));

        List<NewsPost> newsPostsFilteredByAuthor = new ArrayList<>();
        if (author != null && !author[0].equals("[]")) {
            newsPosts.stream()
                    .filter(newsPost ->
                            Arrays.stream(author)
                                    .anyMatch(authorName ->
                                            authorName
                                                    .equals(newsPost.getJournalist().getUsername())))
                    .forEach(newsPostsFilteredByAuthor::add);

            if (newsPostsFilteredByAuthor.size() == 0) {
                log.warn("No news found");
                throw new InvalidQueryException();
            }

            return sorted(newsPostsFilteredByAuthor)
                    .stream()
                    .map(newsConverter::toDto)
                    .collect(Collectors.toList());
        }

        return sorted(newsPosts)
                .stream()
                .map(newsConverter::toDto)
                .collect(Collectors.toList());
    }

    private List<NewsPost> sorted(List<NewsPost> newsPosts) {
        newsPosts.sort((newsPost1, newsPost2) -> newsPost2.getPublishedDate().compareTo(newsPost1.getPublishedDate()));
        return newsPosts;
    }

    private boolean verifyValidQuery(String[] categories) {
        return categories != null && categories.length > 0 ;
    }

    public void rateNews(String title, RatingDto ratingDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Long userId = userRepo.findByUsername(username).getId();

        NewsPost newsPost = newsRepo.findByTitleURL(title).orElseThrow(NewsNotFoundException::new);

        if(ratingTrackerRepo.findByUserId(userId) != null) {
            if(Objects.equals(newsPost.getId(), ratingTrackerRepo.findByUserId(userId).getNewsId())){
                log.warn("User already rated this news");
                throw new UserAlreadyRatedException(username, title);
            }
        }


        newsPost.getRating()
                .setCounter(
                        newsPost.getRating().getCounter() == null ?
                                1 :
                                newsPost.getRating().getCounter() + 1);

        newsPost.getRating().setBiasedRating(newsPost.getRating().getBiasedRating() + ratingDto.getBiasedRating());
        newsPost.getRating().setWritingQuality(newsPost.getRating().getWritingQuality() + ratingDto.getWritingQuality());
        newsPost.getRating().setTruthfulness(newsPost.getRating().getTruthfulness() + ratingDto.getTruthfulness());
        log.info("Added rating to news post with title: {}", newsPost.getTitle());
        RatingTracker ratingTracker = RatingTracker.builder()
                .newsId(newsPost.getId())
                .userId(userId)
                .build();

        ratingTrackerRepo.save(ratingTracker);
        newsRepo.save(newsPost);
    }

    public List<NewsPostDto> findAllNewsByCategory(String[] categories) {

        if(categories == null || categories[0].equals("[]")) {
            log.warn("No categories provided");
            throw new InvalidQueryException();
        }

        List<NewsPost> newsPosts = new ArrayList<>();
        List<ExternalNews> externalNewsList = new ArrayList<>();
        List<NewsPostDto> newsPostDtoList = new ArrayList<>();



        Arrays.stream(categories).forEach(category ->
                newsPosts.addAll(newsRepo.findByCategories(categoryRepo.findByName(category))));

        Arrays.stream(categories).forEach(category ->
                externalNewsList.addAll(externalNewsRepo.findByCategory(categoryRepo.findByName(category))));

        newsPosts.stream().map(newsConverter::toDto).forEach(newsPostDtoList::add);
        externalNewsList.stream().map(newsConverter::toDtoFromExternalNews).forEach(newsPostDtoList::add);

        if(newsPostDtoList.size() == 0) {
            log.warn("No news posts found");
            throw new NewsNotFoundException();
        }

        return newsPostDtoList;

    }

    public List<NewsPostDto> findMyNews(String username) {
        User user = userRepo.findByUsername(username);
        if(user == null) {
            log.warn("User with username: {} not found", username);
            throw new UserNotFoundException(username);
        }

        if(!(user instanceof Journalist)) {
            log.warn("User with username: {} is not a journalist", username);
            throw new NotAJournalistException(username);
        }

        return newsRepo.findByJournalist(user).stream().map(newsConverter::toDto).toList();
    }

    public void editNews(String username, EditNewsDto editNewsDto) {
        NewsPost newsPost = newsRepo.findByTitle(editNewsDto.getTitle()).orElseThrow(NewsNotFoundException::new);
        if(!newsPost.getJournalist().getUsername().equals(username)) {
            log.warn("User with username: {} is not the author of news post with title: {}", username, newsPost.getTitle());
            throw new ParametersDontMatchException(username, newsPost.getTitle());
        }

        newsPost.setTitle(editNewsDto.getTitle());

        if(!checkForValidEditBody(editNewsDto)) {
            log.warn("Only changing news title...");
        }

        if(checkForEmptyEditBody(editNewsDto)) {
            log.warn("User sent empty parameters, nothing to edit...");

        } else {
            // These ternary statements are to prevent null pointer exceptions

            newsPost.setContent(editNewsDto.getContent() == null ? newsPost.getContent() : editNewsDto.getContent());

            newsPost.setImageURL(editNewsDto.getImageURL() == null ? newsPost.getImageURL() : editNewsDto.getImageURL());
        }

        newsRepo.save(newsPost);

        log.info("Edited news post with title: {}", newsPost.getTitle());
    }

    private boolean checkForEmptyEditBody(EditNewsDto editNewsDto) {
        return editNewsDto.getContent().equals("") || editNewsDto.getImageURL().equals("");
    }

    private boolean checkForValidEditBody(EditNewsDto editNewsDto) {
        return editNewsDto.getContent() == null && editNewsDto.getImageURL() == null;
    }

    public void deleteNews(Long id) {
        NewsPost newsPost = newsRepo.findById(id).orElseThrow(NewsNotFoundException::new);
        newsRepo.delete(newsPost);
        log.info("Deleted news post with id: {}", id);
    }

    public NewsPostDto getNews(String title) {

        NewsPost newsPost = newsRepo.findByTitleURL(title).orElseThrow(NewsNotFoundException::new);

        return newsConverter.toDto(newsPost);
    }
}
