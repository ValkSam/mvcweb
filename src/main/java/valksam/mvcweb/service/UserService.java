package valksam.mvcweb.service;

import valksam.mvcweb.model.User;

/**
 * Created by Valk on 12.01.16.
 */
public interface UserService {
    User get(int id);
    boolean delete(int id);
    User save(User user);
}
