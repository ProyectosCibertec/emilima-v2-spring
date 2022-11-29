package pe.com.emilima.dms.service.impl;

import org.apache.catalina.core.ApplicationPart;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pe.com.emilima.dms.model.File;
import pe.com.emilima.dms.repository.FileRepository;
import pe.com.emilima.dms.service.FileService;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Value("${file.uploads.folder}")
    private String fileUploadsLocation;
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

    @Override
    @Transactional
    public File upload(MultipartFile multipartFile) throws IOException {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        byte[] fileNameInBytes = Objects.requireNonNull(multipartFile.getOriginalFilename()).getBytes();
        String fileName = new String(fileNameInBytes, StandardCharsets.UTF_8);
        String fileNameWithUuid = uuidString + "." + FilenameUtils.getExtension(fileName);

        multipartFile.transferTo(new java.io.File(fileUploadsLocation + java.io.File.separator + fileNameWithUuid));

        File file = new File();

        file.setId(uuidString);
        file.setFilename(fileName);

        return file;
    }
}
