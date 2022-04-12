package app.youngmon.project1.user.repository;

import app.youngmon.project1.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMemoryRepository implements UserRepository {
    private Map<Long, User> userRepository;
    private Long cnt = 1L;

    public UserMemoryRepository() {
        userRepository = new HashMap<>();
    }
    @Override
    public Long save(User user) {
        userRepository.put(cnt, user);
        return cnt++;
    }
    @Override
    public User findById(Long id) {
        return userRepository.get(id);
    }
}
