package valksam.mvcweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import valksam.mvcweb.LoggerWrapper;
import valksam.mvcweb.model.User;
import valksam.mvcweb.repository.UserRepository;

/**
 * Created by Valk on 14.01.16.
 */
@Service
public class UserServiceImpl implements UserService {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User get(int id) {
        User user = userRepository.get(id);
        if (user == null) throw LOG.getNotFoundExeption("User with id = " + id + " not found !");
        return user;
    }

    @Transactional
    public boolean delete(int id) {
        boolean result = userRepository.delete(id);
        if (!result) throw LOG.getNotFoundExeption("User with id = " + id + " not found !");
        return result;
    }

    @Transactional
    public User save(User user) {
        User result = userRepository.save(user);
        return result;
    }

}
