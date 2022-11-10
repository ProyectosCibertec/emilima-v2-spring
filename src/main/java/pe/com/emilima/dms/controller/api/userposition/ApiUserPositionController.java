package pe.com.emilima.dms.controller.api.userposition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.com.emilima.dms.model.UserPosition;
import pe.com.emilima.dms.service.UserPositionService;

@RestController
@RequestMapping(value = "/api/user-position")
public class ApiUserPositionController {
    @Autowired
    private UserPositionService userPositionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<UserPosition> index() {
        return userPositionService.findAll();
    }
}
