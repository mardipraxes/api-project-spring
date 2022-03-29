package mindswap.academy.app.persistance.repository;

import mindswap.academy.app.persistance.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Long> {

}
