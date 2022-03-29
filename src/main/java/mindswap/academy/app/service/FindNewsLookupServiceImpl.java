package mindswap.academy.app.service;

import mindswap.academy.app.persistance.model.NewsPost;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class FindNewsLookupServiceImpl implements FindNewsLookupService {

    private static final String FINDNEWS_URL =
            "http://api.mediastack.com/v1/news?access_key=f5aa023260fd566807064a40468feb59&" +
                    "languages=%s" +
                    "&keywords=%s" +
                    "&sources=%s" +
                    "&categories=%s" +
                    "&countries=%s" +
                    "&offset=0&limit=25";



    private final RestTemplate restTemplate;

    public FindNewsLookupServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public String getFindnewsUrl(String languages, String categories, String sources, String countries, String keywords) {
        return String.format(FINDNEWS_URL, languages, categories, sources, countries, keywords);
    }

    @Async
    public CompletableFuture<NewsPost> findNews(String languages, String categories, String sources, String countries, String keywords) {
        String url = getFindnewsUrl(languages, categories, sources, countries, keywords);
        NewsPost result = restTemplate.getForObject(url, NewsPost.class);
        return CompletableFuture.completedFuture(result);
    }
}

