package hr.tvz.zendeli.vaxapp.security.userAuthority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserAuthorityJpaRepository extends JpaRepository<UserAuthority,Long> {
    List<UserAuthority> findAll();

}
