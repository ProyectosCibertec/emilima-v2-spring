package pe.com.emilima.dms.controller.api.documentrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.emilima.dms.model.DocumentRequest;
import pe.com.emilima.dms.model.DocumentalSerie;
import pe.com.emilima.dms.service.DocumentRequestService;
import pe.com.emilima.dms.service.ReportService;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/document-request")
public class ApiDocumentRequestController {
    @Autowired
    private DocumentRequestService documentRequestService;
    @Autowired
    private ReportService<DocumentRequest> reportService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<DocumentRequest> list() {
        return documentRequestService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<DocumentRequest> find(@PathVariable("id") BigInteger id) {
        return documentRequestService.findById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public DocumentRequest save(@RequestBody DocumentRequest documentRequest) {
        return documentRequestService.save(documentRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id) {
        documentRequestService.deleteById(id);
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public void generateReport(HttpServletResponse response) {
        reportService.generateReportAndDownload(response, "base/reporte-solicitudes-documentales.jrxml", (List<DocumentRequest>) documentRequestService.findAll());
    }
}
