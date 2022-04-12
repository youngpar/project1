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
		UserRepository userRepository = new UserMemoryRepository();
		UserService userService = new UserServiceImpl(userRepository);

		User user = new User(1L, "youngseo321", "dudtj9910");
		Long origin = userService.join(user);

		User user2 = userService.findById(1L);
		System.out.println("user = " + user);
		System.out.println("user2 = " + user2);

		SpringApplication.run(Project1Application.class, args);
	}

}
