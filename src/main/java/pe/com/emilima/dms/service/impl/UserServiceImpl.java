package pe.com.emilima.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import pe.com.emilima.dms.model.User;
import pe.com.emilima.dms.repository.UserRepository;
import pe.com.emilima.dms.service.UserService;

import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User entity) {
        if (Objects.isNull(entity))
            return null;

        return userRepository.save(entity);
    }

    @Override
    public Optional<User> findById(String id) {
        if (StringUtils.hasText(id))
            return Optional.empty();

        return userRepository.findById(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
