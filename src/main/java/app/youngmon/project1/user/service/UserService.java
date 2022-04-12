package app.youngmon.project1.user.service;

import app.youngmon.project1.user.User;

import java.util.List;

public interface UserService {
    Long join(User user);
    User findById(Long id);
    List<User> findAll();
}
