package mindswap.academy.app.controller;

import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.commands.RatingDto;
import mindswap.academy.app.persistance.model.NewsPost;
import mindswap.academy.app.service.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsServiceImpl newsService;

    @PostMapping("/post")
    private ResponseEntity<?> postNews(@Valid @RequestBody NewsPostDto newsPostDto){

        newsService.postNews(newsPostDto);

        return ResponseEntity.ok().body("News Posted");
    }

    @GetMapping("/search")
    private ResponseEntity<?> getNews(@RequestParam(value = "categories",defaultValue = "[]") String[] categories,
                                      @RequestParam(value = "author",defaultValue = "[]") String[] author) {

        List<NewsPostDto> foundNews = newsService.findNews(categories, author);

        return ResponseEntity.ok().body(newsService.findNews(categories, author));

    }

    @PostMapping("/rate/{title}")
    private ResponseEntity<?> rateNews(@PathVariable("title") String title,
                                       @RequestBody RatingDto ratingDto) {

        newsService.rateNews(title,ratingDto);

        return ResponseEntity.ok().body("News Rated");
    }
}

