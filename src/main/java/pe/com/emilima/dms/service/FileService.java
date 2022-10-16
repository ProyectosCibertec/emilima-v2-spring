package pe.com.emilima.dms.service;

import pe.com.emilima.dms.model.File;

import java.util.Optional;

public interface FileService {
    File save(File entity);

    Optional<File> findById(String id);

    Iterable<File> findAll();

    void deleteById(String id);
}
