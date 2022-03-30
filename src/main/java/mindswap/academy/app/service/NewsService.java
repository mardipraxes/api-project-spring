package mindswap.academy.app.service;

import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.persistance.model.NewsPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {
    List<NewsPostDto> findNews(String[] categories, String[] author);
}
