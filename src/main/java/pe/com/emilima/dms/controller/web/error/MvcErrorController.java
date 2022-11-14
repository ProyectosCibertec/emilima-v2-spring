package pe.com.emilima.dms.controller.web.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/error")
public class MvcErrorController {
    @RequestMapping(value = "/401", method = RequestMethod.GET)
    public String index(Model model) {
        return "error/error401";
    }
}
