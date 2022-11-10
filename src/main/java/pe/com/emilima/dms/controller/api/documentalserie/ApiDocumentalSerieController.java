package pe.com.emilima.dms.controller.api.documentalserie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.emilima.dms.model.DocumentalSerie;
import pe.com.emilima.dms.service.DocumentalSerieService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/documental-serie")
public class ApiDocumentalSerieController {
    @Autowired
    private DocumentalSerieService documentalSerieService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<DocumentalSerie> list() {
        return documentalSerieService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<DocumentalSerie> find(@PathVariable("id") String id) {
        return documentalSerieService.findById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public DocumentalSerie save(@RequestBody DocumentalSerie documentRequest) {
        return documentalSerieService.save(documentRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        documentalSerieService.deleteById(id);
    }
}
