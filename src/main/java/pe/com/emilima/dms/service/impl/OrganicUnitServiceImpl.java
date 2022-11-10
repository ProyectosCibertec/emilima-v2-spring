package pe.com.emilima.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.emilima.dms.model.OrganicUnit;
import pe.com.emilima.dms.repository.OrganicUnitRepository;
import pe.com.emilima.dms.service.OrganicUnitService;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrganicUnitServiceImpl implements OrganicUnitService {
    @Autowired
    private OrganicUnitRepository organicUnitRepository;

    @Override
    public OrganicUnit save(OrganicUnit entity) {
        if (Objects.isNull(entity))
            return null;

        return organicUnitRepository.save(entity);    }

    @Override
    public Optional<OrganicUnit> findById(BigInteger id) {
        if (id == null)
            return Optional.empty();

        return organicUnitRepository.findById(id);
    }

    @Override
    public Iterable<OrganicUnit> findAll() {
        return organicUnitRepository.findAll();
    }

    @Override
    public void deleteById(BigInteger id) {
        organicUnitRepository.deleteById(id);
    }
}
