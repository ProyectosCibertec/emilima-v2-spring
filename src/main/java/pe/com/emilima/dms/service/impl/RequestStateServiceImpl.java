package pe.com.emilima.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pe.com.emilima.dms.model.RequestState;
import pe.com.emilima.dms.repository.RequestStateRepository;
import pe.com.emilima.dms.service.RequestStateService;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public class RequestStateServiceImpl implements RequestStateService {
    @Autowired
    private RequestStateRepository requestStateRepository;

    @Override
    public RequestState save(RequestState entity) {
        if (Objects.isNull(entity))
            return null;

        return requestStateRepository.save(entity);
    }

    @Override
    public Optional<RequestState> findById(BigInteger id) {
        if (id == null)
            return Optional.empty();

        return requestStateRepository.findById(id);
    }

    @Override
    public Iterable<RequestState> findAll() {
        return requestStateRepository.findAll();
    }

    @Override
    public void deleteById(BigInteger id) {
        requestStateRepository.deleteById(id);
    }
}
