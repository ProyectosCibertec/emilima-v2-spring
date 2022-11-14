package pe.com.emilima.dms.controller.web.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MvcLoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index() {
        return "auth/login";
    }
}
