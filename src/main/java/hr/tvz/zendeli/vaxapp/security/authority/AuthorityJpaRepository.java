package hr.tvz.zendeli.vaxapp.security.authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorityJpaRepository extends JpaRepository<Authority,Long> {


    List<Authority> findAll();
    List<Authority> findAuthorityByName(String name);

}
