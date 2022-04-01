package mindswap.academy.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mindswap.academy.app.commands.NewsFindDto;
import mindswap.academy.app.commands.NewsJsonDto;
import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.converters.NewsConverter;
import mindswap.academy.app.exceptions.NewsNotFoundException;
import mindswap.academy.app.persistance.model.ExternalNews;
import mindswap.academy.app.persistance.model.NewsPost;
import mindswap.academy.app.persistance.repository.ExternalNewsRepo;
import mindswap.academy.app.persistance.repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class FindNewsLookupServiceImpl implements FindNewsLookupService {
    @Autowired
    private NewsConverter newsConverter;
    @Autowired
    private ExternalNewsRepo externalNewsRepo;

    private static final String FINDNEWS_URL =
            "http://api.mediastack.com/v1/news?access_key=f5aa023260fd566807064a40468feb59&language=%s&categories=%s&countries=%s&offset=0&limit=25";

    private final RestTemplate restTemplate;

    public FindNewsLookupServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public String getFindNewsUrl(String languages, String categories, String countries) {
        return String.format(FINDNEWS_URL, languages, categories, countries);
    }

    @Async
    public CompletableFuture<List<NewsFindDto>> findNews(String languages, String categories, String countries) {
        String url = getFindNewsUrl(languages, categories, countries);
        NewsJsonDto result = restTemplate.getForObject(url, NewsJsonDto.class);
        saveNews(result);
        return CompletableFuture.completedFuture(Arrays.asList(result.getData()));
    }

    public void saveNews(NewsJsonDto newsPosts) {
        if(newsPosts.getData() == null) {
            log.warn("No news found");
            throw new NewsNotFoundException();
        }

        NewsFindDto[] newsFindDtos = newsPosts.getData();

        List<ExternalNews> externalNews = new ArrayList<>();

        Arrays.stream(newsFindDtos)
                .map(newsConverter::toExternalNewsEntity)
                .forEach(externalNews::add);

        externalNewsRepo.saveAll(externalNews);

    }
}

