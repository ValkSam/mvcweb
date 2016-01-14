package valksam.mvcweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import valksam.mvcweb.model.User;
import valksam.mvcweb.repository.UserRepository;

/**
 * Created by Valk on 14.01.16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User get(int id) {
        return userRepository.get(id);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

}
