package valksam.mvcweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import valksam.mvcweb.LoggerWrapper;
import valksam.mvcweb.model.User;
import valksam.mvcweb.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model,
                        @RequestParam(value = "error", required = false) boolean error){
        model.addAttribute("error", error);
        LOG.debug("redirect to login form");
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logout (HttpServletRequest request) {
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "redirect:login?logout";
    }

    @RequestMapping(value="")
    public String stab(Model model, HttpServletRequest request){
        String path = request.getPathInfo();
        LOG.debug("request mapping doesn't found. Send stab response to the query " + path);
        model.addAttribute("message", "stab response from " + this.getClass().getName());
        return "stab";
    }

}
