package valksam.mvcweb.repository.springJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import valksam.mvcweb.LoggerWrapper;
import valksam.mvcweb.model.Role;
import valksam.mvcweb.model.User;
import valksam.mvcweb.repository.UserRepository;

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

    /*private static final BeanPropertyRowMapper<User> ROW_MAPPER1 = new BeanPropertyRowMapper<User>(){
        @Override
        public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
            return new User(rs.getInt("id"), rs.getString("name"), rs.getDate("birth_date"), rs.getString("mail"), Role.getRole(rs.getInt("role")), rs.getString("password"));
        }
    };*/
    private static final RowMapper<User> ROW_MAPPER =
            (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getDate("birth_date"), rs.getString("mail"), Role.getRole(rs.getInt("role")), rs.getString("password"));

    @Autowired
    public SpringJdbcUserRepositoryImpl(javax.sql.DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("USERS")
                .usingGeneratedKeyColumns("id");
    }

    @Transactional
    public User get(int id) {
        Role r = Role.ROLE_ADMIN;
        LOG.debug("get(" + id + ")");
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        User user = DataAccessUtils.singleResult(users);
        LOG.debug("retrieved user " + id);
        return user;
    }

    @Transactional
    public boolean delete(int id) {
        LOG.debug("delete(" + id + ")");
        boolean result = jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
        LOG.debug("deleted user " + id);
        return result;
    }

    @Transactional
    public User save(User user) {
        LOG.debug("save(" + user.getId() + ")");
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("birth_date", user.getBirthDate())
                .addValue("mail", user.getMail())
                .addValue("role", user.getRole().getIdx())
                .addValue("password", user.getPassword());
        if (user.isNew()) {
            Number id = insertUser.executeAndReturnKey(map);
            if (id == null) return null;
            user.setId((Integer) id);
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE users SET name=:name, birth_date=:birth_date, mail=:mail, " +
                            "role=:role, password=:password WHERE id=:id", map);
        }
        LOG.debug("saved(" + user.getId() + ")");
        return user;
    }

    @Transactional
    public List<User> getAll() {
        LOG.debug("getAll()");
        List<User> users = jdbcTemplate.query("SELECT * FROM users ORDER BY name", ROW_MAPPER);
        LOG.debug("retrieved user list ");
        return users;
    }


}
