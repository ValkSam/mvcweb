package valksam.mvcweb.repository.springJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import valksam.mvcweb.LoggerWrapper;
import valksam.mvcweb.model.User;
import valksam.mvcweb.repository.UserRepository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Valk on 12.01.16.
 */

@Repository
public class SpringJdbcUserRepositoryImpl implements UserRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(SpringJdbcUserRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    @Autowired
    public SpringJdbcUserRepositoryImpl(javax.sql.DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("USERS")
                .usingGeneratedKeyColumns("id");
    }

    public User get(int id) {
        LOG.debug("get("+id+")");
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        User user = DataAccessUtils.singleResult(users);
        LOG.debug("retrieved user "+id);
        return user;
    }

    public boolean delete(int id) {
        LOG.debug("delete("+id+")");
        boolean result = jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
        LOG.debug("deleted user "+id);
        return result;
    }

    public User save(User user) {
        return null;
    }


}
