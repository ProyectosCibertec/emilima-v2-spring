package pe.com.emilima.dms.service;

import org.springframework.web.multipart.MultipartFile;
import pe.com.emilima.dms.model.File;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface FileService {
    File save(File entity);

    Optional<File> findById(String id);

    Iterable<File> findAll();

    void deleteById(String id);

    File upload(MultipartFile file) throws IOException;
}
