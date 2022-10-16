package pe.com.emilima.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pe.com.emilima.dms.model.DocumentRequest;
import pe.com.emilima.dms.repository.DocumentRequestRepository;
import pe.com.emilima.dms.service.DocumentRequestService;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public class DocumentRequestServiceImpl implements DocumentRequestService {
    @Autowired
    private DocumentRequestRepository documentRequestRepository;

    @Override
    public DocumentRequest save(DocumentRequest entity) {
        if (Objects.isNull(entity))
            return null;

        return documentRequestRepository.save(entity);
    }

    @Override
    public Optional<DocumentRequest> findById(BigInteger id) {
        if (id == null)
            return Optional.empty();

        return documentRequestRepository.findById(id);
    }

    @Override
    public Iterable<DocumentRequest> findAll() {
        return documentRequestRepository.findAll();
    }

    @Override
    public void deleteById(BigInteger id) {
        documentRequestRepository.deleteById(id);
    }
}
