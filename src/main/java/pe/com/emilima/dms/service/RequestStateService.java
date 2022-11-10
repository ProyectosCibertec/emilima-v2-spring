package pe.com.emilima.dms.service;

import pe.com.emilima.dms.model.RequestState;

import java.math.BigInteger;
import java.util.Optional;

public interface RequestStateService {
    RequestState save(RequestState entity);

    Optional<RequestState> findById(BigInteger id);

    Iterable<RequestState> findAll();

    void deleteById(BigInteger id);
}
