package pe.com.emilima.dms.controller.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.com.emilima.dms.model.User;
import pe.com.emilima.dms.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
public class ApiUserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<User> list() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<User> find(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        userService.deleteById(id);
    }
}
