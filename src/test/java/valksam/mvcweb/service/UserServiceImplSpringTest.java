package valksam.mvcweb.service;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.context.util.TestContextResourceUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import valksam.mvcweb.Profiles;
import valksam.mvcweb.model.Role;
import valksam.mvcweb.model.User;
import valksam.mvcweb.util.exception.NotFoundException;

import java.util.Date;

/**
 * Created by Valk on 15.01.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@ActiveProfiles({Profiles.POSTGRES, Profiles.HIBERNATE})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceImplSpringTest {
    @Autowired
    private UserService userService;

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

    @Test
    public void testDelete() throws Exception {
        Integer id = 100000;
        boolean result = userService.delete(id);
        Assert.assertEquals(true, result);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        Integer id = 1;
        boolean result = userService.delete(id);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testInsert() throws Exception {
        User user = new User(null, "Masha", new Date(), "asd@zxc", Role.ROLE_USER, "пароль 3");
        User result = userService.save(user);
        Assert.assertEquals("Masha", result.getName());
    }
}

