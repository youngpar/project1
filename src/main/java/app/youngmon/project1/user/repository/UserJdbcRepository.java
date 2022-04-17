package app.youngmon.project1.user.repository;

import app.youngmon.project1.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class UserJdbcRepository implements UserRepository {

    private DataSource dataSource;

    @Autowired
    public UserJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Long save(User user) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
