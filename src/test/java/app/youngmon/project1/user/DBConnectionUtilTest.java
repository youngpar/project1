package app.youngmon.project1.user;

import app.youngmon.project1.user.repository.DBConnectionUtil;
import app.youngmon.project1.user.repository.UserJdbcRepository;
import app.youngmon.project1.user.repository.UserRepository;
import app.youngmon.project1.user.repository.UserRepositoryV0;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
class DBConnectionUtilTest {
    UserRepository repository = new UserRepositoryV0();

    @Test
    void connection() {
        Connection conn = DBConnectionUtil.getConnection();
        Assertions.assertThat(conn).isNotNull();
    }

    @Test
    void 회원가입() throws SQLException {
        //  give
        User user = new User();
        user.setId(1L);
        user.setUserId("test");
        user.setUserPw("testpw");

        //  when
        repository.save(user);

        //  then
    }

    @Test
    void 찾기() throws SQLException {
        User user = repository.findById(1L);
        log.info("user : ", user);

        Assertions.assertThat(user.getId()).isEqualTo(1L);
    }
}
