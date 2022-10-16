package pe.com.emilima.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pe.com.emilima.dms.model.UserRole;
import pe.com.emilima.dms.repository.UserRoleRepository;
import pe.com.emilima.dms.service.UserRoleService;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRole save(UserRole entity) {
        if (Objects.isNull(entity))
            return null;

        return userRoleRepository.save(entity);
    }

    @Override
    public Optional<UserRole> findById(BigInteger id) {
        if (id == null)
            return Optional.empty();

        return userRoleRepository.findById(id);
    }

    @Override
    public Iterable<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public void deleteById(BigInteger id) {
        userRoleRepository.deleteById(id);
    }
}
