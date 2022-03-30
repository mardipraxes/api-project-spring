package mindswap.academy.app.controller;

import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.persistance.model.NewsPost;
import mindswap.academy.app.service.FindNewsLookupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
@Slf4j
public class FindNewsController {

    @Autowired
    private FindNewsLookupServiceImpl findNewsLookupServiceImpl;

    @GetMapping("/findnews/")
    public ResponseEntity<NewsPostDto> getFindNewsByParameters(
            @RequestParam(value = "languages", defaultValue = "en") String languages,
            @RequestParam(value = "sources", defaultValue = "cnn") String sources,
            @RequestParam(value = "categories", defaultValue = "sports") String categories,
            @RequestParam(value = "keywords", defaultValue = "war") String keywords,
            @RequestParam(value = "countries", defaultValue = "us") String countries
    )

            throws ExecutionException, InterruptedException {
        log.info("Query for news api made with following parameters: {},{},{},{},{}",languages, sources, categories, keywords, countries);
        CompletableFuture<NewsPostDto> newsPost = findNewsLookupServiceImpl.findNews(languages, sources, categories, keywords, countries);
        NewsPostDto newsPostDto = newsPost.get();
        if(newsPostDto == null) {
            return ResponseEntity.badRequest().body(null);
        }
        findNewsLookupServiceImpl.saveNews(newsPostDto);
        return ResponseEntity.ok(newsPostDto);
        // return  gitHubLookupService.findUser(githubId);
    }


   /* @GetMapping("/findNews/{findNewsById}")
    public NewsPost getFindNewsByBody(@PathVariable String findNewsById) throws ExecutionException, InterruptedException {
        System.out.println("before newspostapi Id: " + findNewsById);
        CompletableFuture<NewsPost> newsPost = findNewsLookupServiceImpl.findNews();
        System.out.println("after newspostapi Id: " + findNewsById);
        NewsPost newsPostApi = newsPost.get();
        System.out.println("end newspost Id: " + findNewsById);
        return newsPostApi;
        // return  gitHubLookupService.findUser(githubId);
    }*/

    }



