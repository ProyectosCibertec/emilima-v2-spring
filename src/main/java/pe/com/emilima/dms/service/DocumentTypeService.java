package pe.com.emilima.dms.service;

import pe.com.emilima.dms.model.DocumentType;

import java.math.BigInteger;
import java.util.Optional;

public interface DocumentTypeService {
    DocumentType save(DocumentType entity);

    Optional<DocumentType> findById(BigInteger id);

    Iterable<DocumentType> findAll();

    void deleteById(BigInteger id);
}
