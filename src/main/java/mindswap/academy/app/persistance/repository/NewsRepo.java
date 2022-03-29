package mindswap.academy.app.persistance.repository;

import mindswap.academy.app.persistance.model.NewsPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<NewsPost, Long> {

    NewsPost findByTitle(String title);
}
