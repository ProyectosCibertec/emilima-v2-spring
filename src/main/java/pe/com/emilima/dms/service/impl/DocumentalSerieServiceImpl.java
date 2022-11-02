package pe.com.emilima.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pe.com.emilima.dms.model.DocumentalSerie;
import pe.com.emilima.dms.repository.DocumentalSerieRepository;
import pe.com.emilima.dms.service.DocumentalSerieService;

import java.util.Objects;
import java.util.Optional;

@Service
public class DocumentalSerieServiceImpl implements DocumentalSerieService {
    @Autowired
    private DocumentalSerieRepository documentalSerieRepository;

    @Override
    public DocumentalSerie save(DocumentalSerie entity) {
        if (Objects.isNull(entity))
            return null;

        return documentalSerieRepository.save(entity);
    }

    @Override
    public Optional<DocumentalSerie> findById(String id) {
        if (StringUtils.hasText(id))
            return Optional.empty();

        return documentalSerieRepository.findById(id);
    }

    @Override
    public Iterable<DocumentalSerie> findAll() {
        return documentalSerieRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        documentalSerieRepository.deleteById(id);
    }
}
