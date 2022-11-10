package pe.com.emilima.dms.controller.web.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class MvcLoginController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "auth/login";
    }
}
