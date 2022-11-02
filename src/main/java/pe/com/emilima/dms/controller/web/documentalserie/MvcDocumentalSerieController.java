package pe.com.emilima.dms.controller.web.documentalserie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/series-documentales")
public class MvcDocumentalSerieController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "documental-serie/index";
    }
}
