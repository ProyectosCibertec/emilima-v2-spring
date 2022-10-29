package pe.com.emilima.dms.controller.web.documentrequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/solicitudes")
public class MvcDocumentRequestController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "document-request/index";
    }
}
