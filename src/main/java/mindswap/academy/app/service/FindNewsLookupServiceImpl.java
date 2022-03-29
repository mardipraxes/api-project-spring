package mindswap.academy.app.service;

import mindswap.academy.app.persistance.model.NewsPost;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class FindNewsLookupServiceImpl implements FindNewsLookupService {

    private static final String FINDNEWS_URL = "http://api.mediastack.com/v1/news?access_key=f5aa023260fd566807064a40468feb59&langague=%s&keywords=%s";

    private final RestTemplate restTemplate;

    public FindNewsLookupServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public String getFindnewsUrl(String user) {
        return String.format(FINDNEWS_URL, user);
    }

    @Async
    public CompletableFuture<NewsPost> findNews(String user) {
        String url = getFindnewsUrl(user);
        NewsPost result = restTemplate.getForObject(url, NewsPost.class);
        return CompletableFuture.completedFuture(result);
    }
}

