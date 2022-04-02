package mindswap.academy.app.unit.services;

import lombok.Setter;
import mindswap.academy.app.converters.NewsConverter;
import mindswap.academy.app.persistance.repository.ExternalNewsRepo;
import mindswap.academy.app.service.FindNewsLookupServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FindNewsLookupServiceImplTests {

    @Mock
    private NewsConverter newsConverter;
    @Mock
    private ExternalNewsRepo externalNewsRepo;

    private FindNewsLookupServiceImpl findNewsLookupService;

    private static final String FINDNEWS_URL =
            "http://api.mediastack.com/v1/news?access_key=f5aa023260fd566807064a40468feb59&language=%s&categories=%s&countries=%s&offset=0&limit=25";

    private RestTemplate restTemplate;


    @BeforeEach
    public void setup() {
        this.findNewsLookupService = new FindNewsLookupServiceImpl(new RestTemplateBuilder());
        this.findNewsLookupService.setNewsConverter(newsConverter);
        this.findNewsLookupService.setExternalNewsRepo(externalNewsRepo);
    }

    @Test
    public void testGetFindNewsURL() {

        String expected = String.format(FINDNEWS_URL, "en", "", "");
        assertEquals(expected, findNewsLookupService.getFindNewsUrl("en", "", ""));

    }


}

