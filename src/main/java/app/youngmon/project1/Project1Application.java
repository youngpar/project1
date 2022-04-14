package app.youngmon.project1;

import app.youngmon.project1.user.User;
import app.youngmon.project1.user.repository.UserMemoryRepository;
import app.youngmon.project1.user.repository.UserRepository;
import app.youngmon.project1.user.service.UserService;
import app.youngmon.project1.user.service.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project1Application {

	public static void main(String[] args) {
		SpringApplication.run(Project1Application.class, args);
	}

}
