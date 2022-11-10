package pe.com.emilima.dms.service;

import pe.com.emilima.dms.model.UserPosition;

import java.math.BigInteger;
import java.util.Optional;

public interface UserPositionService {
    UserPosition save(UserPosition entity);

    Optional<UserPosition> findById(BigInteger id);

    Iterable<UserPosition> findAll();

    void deleteById(BigInteger id);
}
