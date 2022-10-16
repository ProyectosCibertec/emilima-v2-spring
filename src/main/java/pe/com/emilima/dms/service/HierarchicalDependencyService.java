package pe.com.emilima.dms.service;

import pe.com.emilima.dms.model.HierarchicalDependency;

import java.math.BigInteger;
import java.util.Optional;

public interface HierarchicalDependencyService {
    HierarchicalDependency save(HierarchicalDependency entity);

    Optional<HierarchicalDependency> findById(BigInteger id);

    Iterable<HierarchicalDependency> findAll();

    void deleteById(BigInteger id);
}
