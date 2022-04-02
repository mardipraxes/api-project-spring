package mindswap.academy.app.persistance.repository;

import mindswap.academy.app.persistance.model.Category;
import mindswap.academy.app.persistance.model.NewsPost;
import mindswap.academy.app.persistance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface NewsRepo extends JpaRepository<NewsPost, Long> {

   Optional<NewsPost> findByTitle(String title);

   Collection<NewsPost> findByCategories(Category category);

   Collection<NewsPost> findByJournalist(User user);

   Optional<NewsPost> findByTitleURL(String title);
}
