package pe.com.emilima.dms.controller.api.userrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.com.emilima.dms.model.UserRole;
import pe.com.emilima.dms.service.UserRoleService;

@RestController
@RequestMapping(value = "/api/user-role")
public class ApiUserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<UserRole> index() {
        return userRoleService.findAll();
    }
}
