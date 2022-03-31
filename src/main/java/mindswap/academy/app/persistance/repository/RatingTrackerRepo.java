package mindswap.academy.app.persistance.repository;

import mindswap.academy.app.persistance.model.RatingTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingTrackerRepo extends JpaRepository<RatingTracker, Long> {

}
