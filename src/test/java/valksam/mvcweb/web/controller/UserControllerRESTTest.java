package valksam.mvcweb.web.controller;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import valksam.mvcweb.Profiles;
import valksam.mvcweb.model.Role;
import valksam.mvcweb.model.User;
import valksam.mvcweb.util.json.JsonUtil;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
@ActiveProfiles({Profiles.POSTGRES, Profiles.SPRINGDATAJPA})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@WebAppConfiguration
public class UserControllerRESTTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @PostConstruct
    void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testGet() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/rest/user/100000"));
        resultActions.andExpect(
                MockMvcResultMatchers
                        .content()
                        .string(JsonUtil.writeValueAndGetJsonAsString(new User(100000, "Вася", new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-28"), "user1@yandex.ru", Role.ROLE_ADMIN, "password1"))));
    }

    @Test
    public void testGetAll() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/rest/user"));

        final List<User> expectedList = new ArrayList<User>(){{
            add(new User(100000, "Вася", null, "user1@yandex.ru", Role.ROLE_ADMIN, "password1"));
            add(new User(100001, "Коля", null, "user2@yandex.ru", Role.ROLE_USER, "passwor2"));
        }};

        resultActions.andExpect(
                MockMvcResultMatchers
                        .content()
                        .string(new BaseMatcher<String>() {
                            @Override
                            public void describeTo(Description description) {
                            }

                            @Override
                            public boolean matches(Object actual) {
                                List<String> actualList = JsonUtil.readValuesListFromString((String) actual, User.class);
                                return expectedList.equals(actualList);
                            }
                        }));
    }

    @Test
    public void testCreate() throws Exception {
        User newUser = new User(null, "Вася-2", null, "user1-2@yandex.ru", Role.ROLE_ADMIN, "password1-2");
        ResultActions resultActions = mockMvc.perform(
                put("/rest/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValueAndGetJsonAsString(newUser)));
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }

}