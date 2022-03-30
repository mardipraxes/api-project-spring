package mindswap.academy.app.service;

import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.converters.NewsConverter;
import mindswap.academy.app.exceptions.InvalidQueryException;
import mindswap.academy.app.exceptions.NewsPostAlreadyExistsException;
import mindswap.academy.app.persistance.model.NewsPost;
import mindswap.academy.app.persistance.repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private NewsConverter newsConverter;


    public void postNews(NewsPostDto newsPostDto) {

        if(newsRepo.findByTitle(newsPostDto.getTitle()).isPresent()) {
            throw new NewsPostAlreadyExistsException(newsPostDto.getTitle());
        }
        newsRepo.save(newsConverter.toEntity(newsPostDto));
    }

    @Override
    public List<NewsPostDto> findNews(String[] categories, String[] author) {

        List<NewsPost> newsPosts = new ArrayList<>();
        if(!verifyValidQuery(categories, author)) {
            throw new InvalidQueryException();
        }

        Arrays.stream(categories).forEach(category -> newsPosts.addAll(newsRepo.findByCategories(category)));
        List<NewsPost> newsPostsFilteredByAuthor = new ArrayList<>();
        if(author != null) {
            newsPosts.stream()
                    .filter(newsPost ->
                            Arrays.stream(author)
                                    .anyMatch(authorName ->
                                            authorName
                                                    .equals(newsPost.getJournalist().getUsername())))
                    .forEach(newsPostsFilteredByAuthor::add);

            if(newsPostsFilteredByAuthor.size() == 0) {
                throw new InvalidQueryException();
            }

            return sorted(newsPostsFilteredByAuthor)
                    .stream()
                    .map(newsPost -> newsConverter.toDto(newsPost))
                    .collect(Collectors.toList());
        }

        return sorted(newsPosts)
                .stream()
                .map(newsPost -> newsConverter.toDto(newsPost))
                .collect(Collectors.toList());
    }

    private List<NewsPost> sorted(List<NewsPost> newsPosts) {
        newsPosts.sort((newsPost1, newsPost2) -> newsPost2.getPublishedDate().compareTo(newsPost1.getPublishedDate()));
        return newsPosts;
    }

    private boolean verifyValidQuery(String[] categories, String[] author) {
        return categories == null;
    }
}
