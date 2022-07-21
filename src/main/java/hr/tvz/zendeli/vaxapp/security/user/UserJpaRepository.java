package hr.tvz.zendeli.vaxapp.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserJpaRepository extends JpaRepository<hr.tvz.zendeli.vaxapp.security.user.User,Long> {

    List<User> findAll();
   hr.tvz.zendeli.vaxapp.security.user.User findUsersByUsername(String username);


}
