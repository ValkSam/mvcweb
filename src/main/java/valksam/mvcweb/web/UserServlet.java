package valksam.mvcweb.web;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import valksam.mvcweb.LoggerWrapper;
import valksam.mvcweb.model.User;
import valksam.mvcweb.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Valk on 10.01.16.
 */
public class UserServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServlet.class);

    private UserService userService;
    private String jspPath = "";

    @Override
    public void init(ServletConfig config) throws ServletException {
        LOG.debug("initializing the servlet");
        super.init(config);
        jspPath = config.getInitParameter("jspPath");
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        userService = (UserService) springContext.getBean("userServiceImpl");
        LOG.debug("the servlet is initialized");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/user": {
                User user = userService.get(100000);
                request.setAttribute("user", user);
                request.getRequestDispatcher(jspPath + "usersList.jsp").forward(request, response);
                LOG.debug("send a response to the query " + path);
                break;
            }
        }
    }
}


//TODO в spring-app.xml убираю <mvc:annotatiоon-driven/> и все равн работает - поонять с каког момента так работает. Или может его изначально можно было не указывать. По идее благодаря этому должен находится сервис, но и без него  находится