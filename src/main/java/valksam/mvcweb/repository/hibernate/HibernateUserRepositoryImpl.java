package valksam.mvcweb.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import valksam.mvcweb.LoggerWrapper;
import valksam.mvcweb.model.Role;
import valksam.mvcweb.model.User;
import valksam.mvcweb.repository.UserRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

//import javax.transaction.Transactional;

/**
 * Created by Valk on 12.01.16.
 */

@Repository
public class HibernateUserRepositoryImpl implements UserRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(HibernateUserRepositoryImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public User get(int id) {
        LOG.debug("get(" + id + ")");
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        LOG.debug("retrieved user " + id);
        return user;
    }

    @Transactional
    public boolean delete(int id) {
        LOG.debug("delete(" + id + ")");
        Session session = sessionFactory.getCurrentSession();
        boolean result = session.createQuery("DELETE User WHERE id=?")
                .setParameter(0, id)
                .executeUpdate() != 0;
        LOG.debug("deleted user " + id);
        return result;
    }

    @org.springframework.transaction.annotation.Transactional
    public User save(User user) {
        LOG.debug("save(" + user.getId() + ")");
        Session session = sessionFactory.getCurrentSession();
        if (user.isNew()) {
            Serializable id = session.save(user);
            if (id == null) return null;
            user.setId((Integer) id);
        } else {
            session.update(user);
        }
        LOG.debug("saved(" + user.getId() + ")");
        return user;
    }


}
