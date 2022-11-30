package pe.com.emilima.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.com.emilima.dms.model.Document;
import pe.com.emilima.dms.repository.DocumentRepository;
import pe.com.emilima.dms.service.DocumentService;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Document save(Document entity) {
        if (Objects.isNull(entity))
            return null;

        return documentRepository.save(entity);
    }

    @Override
    public Optional<Document> findById(BigInteger id) {
        if (id == null)
            return Optional.empty();

        return documentRepository.findById(id);
    }

    @Override
    public Iterable<Document> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public void deleteById(BigInteger id) {
        documentRepository.deleteById(id);
    }
}
