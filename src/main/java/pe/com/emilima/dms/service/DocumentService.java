package pe.com.emilima.dms.service;

import org.springframework.web.multipart.MultipartFile;
import pe.com.emilima.dms.model.Document;

import java.math.BigInteger;
import java.util.Optional;

public interface DocumentService {
    Document save(Document entity);

    Optional<Document> findById(BigInteger id);

    Iterable<Document> findAll();

    void deleteById(BigInteger id);
}
