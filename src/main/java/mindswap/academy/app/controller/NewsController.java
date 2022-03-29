package mindswap.academy.app.controller;

import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.service.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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



}

