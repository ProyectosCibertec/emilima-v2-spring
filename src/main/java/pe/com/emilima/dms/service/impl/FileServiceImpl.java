package pe.com.emilima.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pe.com.emilima.dms.model.File;
import pe.com.emilima.dms.repository.FileRepository;
import pe.com.emilima.dms.service.FileService;

import java.util.Objects;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public File save(File entity) {
        if (Objects.isNull(entity))
            return null;

        return fileRepository.save(entity);
    }

    @Override
    public Optional<File> findById(String id) {
        if (StringUtils.hasText(id))
            return Optional.empty();

        return fileRepository.findById(id);
    }

    @Override
    public Iterable<File> findAll() {
        return fileRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        fileRepository.deleteById(id);
    }
}
