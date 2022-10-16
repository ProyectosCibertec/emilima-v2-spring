package pe.com.emilima.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pe.com.emilima.dms.model.HierarchicalDependency;
import pe.com.emilima.dms.repository.HierarchicalDependencyRepository;
import pe.com.emilima.dms.service.HierarchicalDependencyService;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public class HierarchicalDependencyServiceImpl implements HierarchicalDependencyService {
    @Autowired
    private HierarchicalDependencyRepository hierarchicalDependencyRepository;

    @Override
    public HierarchicalDependency save(HierarchicalDependency entity) {
        if (Objects.isNull(entity))
            return null;

        return hierarchicalDependencyRepository.save(entity);
    }

    @Override
    public Optional<HierarchicalDependency> findById(BigInteger id) {
        if (id == null)
            return Optional.empty();

        return hierarchicalDependencyRepository.findById(id);
    }

    @Override
    public Iterable<HierarchicalDependency> findAll() {
        return hierarchicalDependencyRepository.findAll();
    }

    @Override
    public void deleteById(BigInteger id) {
        hierarchicalDependencyRepository.deleteById(id);
    }
}
