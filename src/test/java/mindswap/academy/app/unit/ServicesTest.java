package mindswap.academy.app.unit;

import mindswap.academy.app.persistance.model.User;
import mindswap.academy.app.persistance.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@MockBean
public class ServicesTest {

    @Mock
    private UserRepo userRepo;


    @Test
    public void test() {

      Optional<User> user = userRepo.findById(10L);


      assertEquals(user.get().getId(), 10L);

    }
}
