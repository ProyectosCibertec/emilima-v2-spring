package pe.com.emilima.dms.service;

import pe.com.emilima.dms.model.User;

import java.util.Optional;

public interface UserService {
    User save(User entity);

    Optional<User> findById(String id);

    Iterable<User> findAll();

    void deleteById(String id);
}
