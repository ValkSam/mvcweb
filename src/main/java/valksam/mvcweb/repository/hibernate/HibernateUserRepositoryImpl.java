package valksam.mvcweb.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import valksam.mvcweb.LoggerWrapper;
import valksam.mvcweb.model.User;
import valksam.mvcweb.repository.UserRepository;

import javax.transaction.Transactional;

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
        LOG.debug("get("+id+")");
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        LOG.debug("retrieved user "+id);
        return user;
    }

    public boolean delete(int id) {
        return false;
    }

    public User save(User user) {
        return null;
    }


}
