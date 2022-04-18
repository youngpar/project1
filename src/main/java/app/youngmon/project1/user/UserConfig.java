package app.youngmon.project1.user;

import app.youngmon.project1.user.repository.UserMemoryRepository;
import app.youngmon.project1.user.repository.UserRepository;
import app.youngmon.project1.user.repository.UserRepositoryV0;
import app.youngmon.project1.user.service.UserService;
import app.youngmon.project1.user.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryV0();
    }
}
