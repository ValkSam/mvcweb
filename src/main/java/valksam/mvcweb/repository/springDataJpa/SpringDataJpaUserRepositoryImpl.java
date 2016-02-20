package valksam.mvcweb.repository.springDataJpa;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import valksam.mvcweb.LoggerWrapper;
import valksam.mvcweb.model.User;
import valksam.mvcweb.repository.UserRepository;

import java.util.List;


/**
 * Created by Valk on 12.01.16.
 */

@Repository
public class SpringDataJpaUserRepositoryImpl implements UserRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(SpringDataJpaUserRepositoryImpl.class);

    @Autowired
    private ProxyUserRepository proxy;

    @Transactional
    public User get(int id) {
        LOG.debug("get(" + id + ")");
        User result = proxy.findOne(id);
        LOG.debug("retrieved user " + id);
        return result;
    }

    @Transactional
    public boolean delete(int id) {
        LOG.debug("delete(" + id + ")");
        boolean result = proxy.delete(id) != 0;
        LOG.debug("deleted user " + id);
        return result;
    }

    @Transactional
    public User save(User user) {
        LOG.debug("save(" + user.getId() + ")");
        proxy.save(user);
        LOG.debug("saved(" + user.getId() + ")");
        return user;
    }

    @Transactional
    public List<User> getAll() {
        LOG.debug("getAll()");
        List<User> result = proxy.findAll(new Sort("name"));
        LOG.debug("retrieved user list ");
        return result;
    }
}
