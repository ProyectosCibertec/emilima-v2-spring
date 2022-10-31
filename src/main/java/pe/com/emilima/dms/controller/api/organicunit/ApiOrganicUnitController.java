package pe.com.emilima.dms.controller.api.organicunit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.com.emilima.dms.model.OrganicUnit;
import pe.com.emilima.dms.service.OrganicUnitService;

@RestController
@RequestMapping(value = "/api/organic-unit")
public class ApiOrganicUnitController {
    @Autowired
    private OrganicUnitService organicUnitService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<OrganicUnit> list() {
        return organicUnitService.findAll();
    }
}
