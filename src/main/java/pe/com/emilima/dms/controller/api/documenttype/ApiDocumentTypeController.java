package pe.com.emilima.dms.controller.api.documenttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.emilima.dms.model.DocumentRequest;
import pe.com.emilima.dms.model.DocumentType;
import pe.com.emilima.dms.service.DocumentTypeService;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/document-type")
public class ApiDocumentTypeController {
    @Autowired
    private DocumentTypeService documentTypeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<DocumentType> list() {
        return documentTypeService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<DocumentType> find(@PathVariable("id") BigInteger id) {
        return documentTypeService.findById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public DocumentType save(@RequestBody DocumentType documentType) {
        return documentTypeService.save(documentType);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id) {
        documentTypeService.deleteById(id);
    }
}
