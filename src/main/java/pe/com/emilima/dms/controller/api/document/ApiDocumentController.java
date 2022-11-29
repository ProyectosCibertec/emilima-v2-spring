package pe.com.emilima.dms.controller.api.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.com.emilima.dms.model.Document;
import pe.com.emilima.dms.model.DocumentRequest;
import pe.com.emilima.dms.model.DocumentType;
import pe.com.emilima.dms.model.DocumentalSerie;
import pe.com.emilima.dms.service.DocumentService;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/document")
public class ApiDocumentController {
    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Document> list() {
        return documentService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Document> find(@PathVariable("id") BigInteger id) {
        return documentService.findById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Document save(
            @RequestPart String serialNumber,
            @RequestPart String name,
            @RequestPart String description,
            @RequestPart String uploadDate,
            @RequestPart String creationDate,
            @RequestPart("documentType") String documentTypeId,
            @RequestPart("documentalSerie") String documentalSerieCode,
            @RequestPart("documentRequest") String documentRequestId,
            @RequestPart MultipartFile file) {
        Document document = new Document();
        DocumentType documentType = new DocumentType();
        DocumentalSerie documentalSerie = new DocumentalSerie();
        DocumentRequest documentRequest = new DocumentRequest();
        documentType.setId(new BigInteger(documentTypeId));
        documentalSerie.setCode(documentalSerieCode);
        documentRequest.setId(new BigInteger(documentRequestId));
        try {
            document.setSerialNumber(new BigInteger(serialNumber));
            document.setName(name);
            document.setDescription(description);
            document.setUploadDate(new SimpleDateFormat().parse(uploadDate));
            document.setCreationDate(new SimpleDateFormat().parse(creationDate));
            document.setDocumentType(documentType);
            document.setDocumentSerie(documentalSerie);
            document.setDocumentRequest(documentRequest);
        } catch (ParseException e) {

        }
        return documentService.save(document);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id) {
        documentService.deleteById(id);
    }
}
