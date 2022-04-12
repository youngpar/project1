package app.youngmon.project1.user;

import app.youngmon.project1.user.repository.UserRepository;
import app.youngmon.project1.user.service.UserService;
import app.youngmon.project1.user.service.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    @Transactional
    void 회원가입() {
        //  given
        User user = new User(1L, "youngseo21", "dudtj9910");

        //  when
        Long id = userService.join(user);
        System.out.println(id);
        User find = userService.findById(1L);

        //  then
        Assertions.assertThat(user).isEqualTo(find);
    }

    @Test
    void 중복() {
        //  given

        //  when

        //  then

    }
}
