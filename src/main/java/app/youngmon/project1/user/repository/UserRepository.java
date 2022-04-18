package app.youngmon.project1.user.repository;

import app.youngmon.project1.user.User;

import java.sql.SQLException;

public interface UserRepository {
    Long save(User user);
    User findById(Long id);
}
