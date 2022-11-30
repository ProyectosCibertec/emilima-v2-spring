package pe.com.emilima.dms.controller.web.document;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/documentos")
public class MvcDocumentController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "document/index";
    }
}
