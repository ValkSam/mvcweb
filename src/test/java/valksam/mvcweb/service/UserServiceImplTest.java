package valksam.mvcweb.service;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import valksam.mvcweb.Profiles;
import valksam.mvcweb.model.User;
import valksam.mvcweb.util.exception.NotFoundException;

public class UserServiceImplTest {
    public static GenericXmlApplicationContext springContext;
    private static UserService userService;

    @BeforeClass
    public static void beforeClass() throws Exception {
        springContext = new GenericXmlApplicationContext();
        springContext.getEnvironment().setActiveProfiles(Profiles.POSTGRES, Profiles.HIBERNATE);
        springContext.load("classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml");
        springContext.refresh();
        userService = (UserService) springContext.getBean("userServiceImpl");
    }

    @AfterClass
    public static void afterClass() throws Exception {
        springContext.close();
    }


    @Test
    public void testGet() throws Exception {
        Integer id = 100000;
        User user = userService.get(id);
        Assert.assertEquals(id, user.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        Integer id = 1;
        User user = userService.get(id);
    }

    /*
    * этот класс для образца. Основной тестовый класс UserServiceImplSpringTest*/


}