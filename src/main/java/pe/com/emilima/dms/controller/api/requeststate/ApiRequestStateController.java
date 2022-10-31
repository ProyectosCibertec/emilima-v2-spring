package pe.com.emilima.dms.controller.api.requeststate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.com.emilima.dms.model.RequestState;
import pe.com.emilima.dms.service.RequestStateService;

@RestController
@RequestMapping(value = "/api/request-state")
public class ApiRequestStateController {
    @Autowired
    private RequestStateService requestStateService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<RequestState> list() {
        return requestStateService.findAll();
    }
}
