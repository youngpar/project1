package app.youngmon.project1.user.service;

import app.youngmon.project1.user.User;
import app.youngmon.project1.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long join(User user) {
        return userRepository.save(user);
    }
    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id);
        return user;
    }
    @Override
    public List<User> findAll() {
        List<User> list = new LinkedList<>();
        return list;
    }
}
