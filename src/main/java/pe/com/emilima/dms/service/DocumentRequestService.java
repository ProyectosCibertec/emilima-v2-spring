package pe.com.emilima.dms.service;

import pe.com.emilima.dms.model.DocumentRequest;

import java.math.BigInteger;
import java.util.Optional;

public interface DocumentRequestService {
    DocumentRequest save(DocumentRequest entity);

    Optional<DocumentRequest> findById(BigInteger id);

    Iterable<DocumentRequest> findAll();

    void deleteById(BigInteger id);
}
