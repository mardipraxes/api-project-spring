package mindswap.academy.app.persistance.repository;

import mindswap.academy.app.persistance.model.Journalist;
import mindswap.academy.app.persistance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    User findByUsername(String author);

    User findByUsernameAndPassword(String username, String password);


    User findByEmail(String email);
}
