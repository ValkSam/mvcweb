package valksam.mvcweb.repository;


import valksam.mvcweb.model.User;

import java.util.List;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public interface UserRepository {
    // null if not found
    User get(int id);

    // false if not found
    boolean delete(int id);

    User save(User user);

    List<User> getAll();

}
