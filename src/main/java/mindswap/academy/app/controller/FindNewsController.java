package mindswap.academy.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.NewsFindDto;
import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.persistance.model.NewsPost;
import mindswap.academy.app.service.FindNewsLookupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class FindNewsController {

    private final FindNewsLookupServiceImpl findNewsLookupServiceImpl;

    @GetMapping("/findnews")
    public ResponseEntity<List<NewsFindDto>> getFindNewsByParameters(
            @RequestParam(value = "languages", defaultValue = "en") String languages,
            @RequestParam(value = "categories", defaultValue = "sports") String categories,
            @RequestParam(value = "countries", defaultValue = "us") String countries
    )
            throws ExecutionException, InterruptedException {
        log.info("Query for news api made with following parameters: {},{},{}",
                languages, categories,countries);

        CompletableFuture<List<NewsFindDto>> newsPost =
                findNewsLookupServiceImpl.findNews(languages, categories, countries);

        return ResponseEntity.ok(newsPost.get());

    }

}



