package valksam.mvcweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import valksam.mvcweb.LoggerWrapper;
import valksam.mvcweb.model.User;
import valksam.mvcweb.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser(Model model, HttpServletRequest request) {
        String path = request.getPathInfo();
        User user = userService.get(100000);
        model.addAttribute("user", user);
        LOG.debug("send a response to the query " + path);
        return "usersList";
    }
}
