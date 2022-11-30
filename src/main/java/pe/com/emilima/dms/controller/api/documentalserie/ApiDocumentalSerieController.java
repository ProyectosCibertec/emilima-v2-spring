package pe.com.emilima.dms.controller.api.documentalserie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.emilima.dms.model.DocumentalSerie;
import pe.com.emilima.dms.service.DocumentalSerieService;
import pe.com.emilima.dms.service.ReportService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/documental-serie")
public class ApiDocumentalSerieController {
    @Autowired
    private DocumentalSerieService documentalSerieService;
    @Autowired
    private ReportService<DocumentalSerie> reportService;

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

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public void generateReport(HttpServletResponse response) {
        reportService.generateReportAndDownload(response, "base/reporte-series-documentales.jrxml", (List<DocumentalSerie>) documentalSerieService.findAll());
    }
}
