package mindswap.academy.app.persistance.repository;

import mindswap.academy.app.persistance.model.NewsPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface NewsRepo extends JpaRepository<NewsPost, Long> {

   Optional<NewsPost> findByTitle(String title);

   Collection<NewsPost> findByCategories(String category);
}
