package pe.com.emilima.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.emilima.dms.model.UserPosition;
import pe.com.emilima.dms.repository.UserPositionRepository;
import pe.com.emilima.dms.service.UserPositionService;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserPositionServiceImpl implements UserPositionService {
    @Autowired
    private UserPositionRepository userPositionRepository;

    @Override
    public UserPosition save(UserPosition entity) {
        if (Objects.isNull(entity))
            return null;

        return userPositionRepository.save(entity);
    }

    @Override
    public Optional<UserPosition> findById(BigInteger id) {
        if (id == null)
            return Optional.empty();

        return userPositionRepository.findById(id);
    }

    @Override
    public Iterable<UserPosition> findAll() {
        return userPositionRepository.findAll();
    }

    @Override
    public void deleteById(BigInteger id) {
        userPositionRepository.deleteById(id);
    }
}
