package mindswap.academy.app;

import mindswap.academy.app.controller.NewsController;
import mindswap.academy.app.persistance.repository.UserRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);

	}


}
