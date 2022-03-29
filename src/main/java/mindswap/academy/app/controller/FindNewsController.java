package mindswap.academy.app.controller;

import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.persistance.model.NewsPost;
import mindswap.academy.app.service.FindNewsLookupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class FindNewsController {

    @Autowired
    private FindNewsLookupServiceImpl findNewsLookupServiceImpl;

    @GetMapping("/findNews/{findNewsById}")
    public NewsPost getFindNewsById(@PathVariable String findNewsById) throws ExecutionException, InterruptedException {
        System.out.println("before Github Id: " + findNewsById);
        CompletableFuture<NewsPost> newsPost = findNewsLookupServiceImpl.findNews(findNewsById);
        System.out.println("after Github Id: " + findNewsById);
        NewsPost userGit = newsPost.get();
        System.out.println("end Github Id: " + findNewsById);
        return userGit;
        // return  gitHubLookupService.findUser(githubId);
    }

    }



