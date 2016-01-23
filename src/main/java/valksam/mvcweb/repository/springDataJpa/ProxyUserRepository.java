package valksam.mvcweb.repository.springDataJpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import valksam.mvcweb.model.User;

import java.util.List;

/**
 * Created by Valk on 18.01.16.
 */
public interface ProxyUserRepository extends JpaRepository<User, Integer>{
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id+1")
    int delete(@Param("id") int id);

    @Override
    User save(User user);

    @Override
    User findOne(Integer id);

    @Override
    List<User> findAll(Sort sort);
}
