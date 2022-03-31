package mindswap.academy.app.persistance.repository;

import mindswap.academy.app.persistance.model.JournalistApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalistApplicationsRepo extends JpaRepository<JournalistApplications, Long> {

    JournalistApplications findByUsername(String username);
}
