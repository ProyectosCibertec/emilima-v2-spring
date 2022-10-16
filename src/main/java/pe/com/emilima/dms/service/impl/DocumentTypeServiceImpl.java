package pe.com.emilima.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pe.com.emilima.dms.model.DocumentType;
import pe.com.emilima.dms.repository.DocumentTypeRepository;
import pe.com.emilima.dms.service.DocumentTypeService;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public class DocumentTypeServiceImpl implements DocumentTypeService {
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public DocumentType save(DocumentType entity) {
        if (Objects.isNull(entity))
            return null;

        return documentTypeRepository.save(entity);
    }

    @Override
    public Optional<DocumentType> findById(BigInteger id) {
        if (id == null)
            return Optional.empty();

        return documentTypeRepository.findById(id);
    }

    @Override
    public Iterable<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }

    @Override
    public void deleteById(BigInteger id) {
        documentTypeRepository.deleteById(id);
    }
}
