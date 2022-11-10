package pe.com.emilima.dms.service;

import pe.com.emilima.dms.model.UserRole;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRoleService {
    UserRole save(UserRole entity);

    Optional<UserRole> findById(BigInteger id);

    Iterable<UserRole> findAll();

    void deleteById(BigInteger id);
}
