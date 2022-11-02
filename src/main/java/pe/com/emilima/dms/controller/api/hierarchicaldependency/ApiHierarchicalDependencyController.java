package pe.com.emilima.dms.controller.api.hierarchicaldependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.com.emilima.dms.model.HierarchicalDependency;
import pe.com.emilima.dms.service.HierarchicalDependencyService;

@RestController
@RequestMapping(value = "/api/hierarchical-dependency")
public class ApiHierarchicalDependencyController {
    @Autowired
    private HierarchicalDependencyService hierarchicalDependencyService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<HierarchicalDependency> list() {
        return hierarchicalDependencyService.findAll();
    }
}
