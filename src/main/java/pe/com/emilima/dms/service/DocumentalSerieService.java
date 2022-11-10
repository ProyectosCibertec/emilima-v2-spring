package pe.com.emilima.dms.service;

import pe.com.emilima.dms.model.DocumentalSerie;

import java.util.Optional;

public interface DocumentalSerieService {
    DocumentalSerie save(DocumentalSerie entity);

    Optional<DocumentalSerie> findById(String id);

    Iterable<DocumentalSerie> findAll();

    void deleteById(String id);
}
