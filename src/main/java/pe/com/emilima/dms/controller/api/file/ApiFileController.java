package pe.com.emilima.dms.controller.api.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pe.com.emilima.dms.model.File;
import pe.com.emilima.dms.service.FileService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;

@RestController
@RequestMapping(value = "/file")
public class ApiFileController {
    @Value("${file.uploads.folder}")
    private String fileUploadsLocation;
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/download/{fileId}", method = RequestMethod.GET)
    public OutputStream downloadFileById(@PathVariable String fileId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File fileToDownload = fileService.findById(fileId).orElse(new File());
        java.io.File file = new java.io.File(fileUploadsLocation + java.io.File.separator + fileToDownload.getId() + "."
                + FilenameUtils.getExtension(fileToDownload.getFilename()));
        byte[] bytes = FileUtils.readFileToByteArray(file);
        response.setContentType("application/octet-stream");
        response.addHeader("Pragma", "public");
        response.addHeader("Cache-Control", "max-age=0");
        response.addHeader("Content-Disposition", MessageFormat.format("attachment; filename={0}", fileToDownload.getFilename()));
        response.setContentLength((int) file.length());

        ServletOutputStream outputStream = response.getOutputStream();

        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();

        return outputStream;
    }
}
