package mindswap.academy.app.service;

import mindswap.academy.app.commands.NewsPostDto;
import mindswap.academy.app.converters.NewsConverter;
import mindswap.academy.app.exceptions.NewsPostAlreadyExistsException;
import mindswap.academy.app.persistance.repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private NewsConverter newsConverter;


    public void postNews(NewsPostDto newsPostDto) {

        if(newsRepo.findByTitle(newsPostDto.getTitle()).isPresent()) {
            throw new NewsPostAlreadyExistsException(newsPostDto.getTitle());
        }
        newsRepo.save(newsConverter.toEntity(newsPostDto));
    }
}
