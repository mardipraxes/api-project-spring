package mindswap.academy.app.persistance.repository;

import mindswap.academy.app.persistance.model.ExternalNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalNewsRepo extends JpaRepository<ExternalNews,Long> {
}
