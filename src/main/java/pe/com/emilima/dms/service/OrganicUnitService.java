package pe.com.emilima.dms.service;

import pe.com.emilima.dms.model.OrganicUnit;

import java.math.BigInteger;
import java.util.Optional;

public interface OrganicUnitService {
    OrganicUnit save(OrganicUnit entity);

    Optional<OrganicUnit> findById(BigInteger id);

    Iterable<OrganicUnit> findAll();

    void deleteById(BigInteger id);
}
