package pe.com.emilima.dms.controller.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class MvcHomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(Model model) {
        return "index";
    }
}
