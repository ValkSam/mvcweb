package valksam.mvcweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import valksam.mvcweb.LoggerWrapper;
import valksam.mvcweb.model.Role;
import valksam.mvcweb.model.User;
import valksam.mvcweb.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class UserControllerREST {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserControllerREST.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    public List<User> getUserList() throws ServletException, IOException {
        return userService.getAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    public User getUser(@PathVariable int id, HttpServletRequest request) throws ServletException, IOException {
        String path = request.getPathInfo();
        User user =  userService.get(id);
        System.out.println(user);
        LOG.debug("send a response to the query " + path);
        return user;
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    public ResponseEntity<User> createUser(@RequestBody User user) throws ServletException, IOException {
        User created = userService.save(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(created, httpHeaders, HttpStatus.CREATED);
    }
}
