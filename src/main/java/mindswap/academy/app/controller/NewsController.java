package mindswap.academy.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.EditNewsDto;
import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.commands.RatingDto;
import mindswap.academy.app.persistance.model.NewsPost;
import mindswap.academy.app.service.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsServiceImpl newsService;

    @PostMapping("/post")
    private ResponseEntity<?> postNews(@Valid @RequestBody NewsPostDto newsPostDto){

        newsService.postNews(newsPostDto);

        return ResponseEntity.ok().body("News Posted");
    }

    @GetMapping("/search")
    private ResponseEntity<?> getNews(@RequestParam(value = "categories",defaultValue = "[]") String[] categories,
                                      @RequestParam(value = "author",defaultValue = "[]") String[] author) {

        List<NewsPostDto> foundNews = newsService.findNews(categories, author);

        return ResponseEntity.ok().body(foundNews);

    }

    @PostMapping("/rate/{title}")
    private ResponseEntity<?> rateNews(@PathVariable("title") String title,
                                       @RequestBody RatingDto ratingDto) {

        newsService.rateNews(title,ratingDto);

        return ResponseEntity.ok().body("News Rated");
    }

    @GetMapping("/{title}")
    private ResponseEntity<NewsPostDto> getNews(@PathVariable("title") String title) {
        log.info("Getting news with title: " + title);
        return ResponseEntity.ok().body(newsService.getNews(title));

    }

    /**
     * This method unlike the search, it returns all the news posts including external news posts.
     *
     * @param categories - categories of the news posts
     * @return a response entity with the list of news posts
     */
    @GetMapping("/find")
    private ResponseEntity<?> getNews(@RequestParam(value = "categories", defaultValue = "[]") String[] categories) {

        List<NewsPostDto> foundNews = newsService.findAllNewsByCategory(categories);

        return ResponseEntity.ok().body(foundNews);

    }

    @GetMapping("/findmynews")
    private ResponseEntity<List<NewsPostDto>> getMyNews() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<NewsPostDto> newsPostDtoList = newsService.findMyNews(username);

        return ResponseEntity.ok().body(newsPostDtoList);

    }

    @PatchMapping("/edit")
    private ResponseEntity<?> editNews(@RequestBody EditNewsDto editNewsDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        newsService.editNews(username, editNewsDto);

        return ResponseEntity.ok().body("News Edited");
    }

    // Needs Admin Role
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteNews(@PathVariable("id") Long id) {

        newsService.deleteNews(id);

        return ResponseEntity.ok().body("News Deleted with id: " + id);
    }
}

