package hr.tvz.zendeli.vaxapp.security.user;

import hr.tvz.zendeli.vaxapp.security.SecurityUtils;
import hr.tvz.zendeli.vaxapp.security.authority.Authority;
import hr.tvz.zendeli.vaxapp.security.userAuthority.UserAuthorityJpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class  UserRepository {

    private final UserJpaRepository userJpaRepository;
 //  private final UserAuthorityJpaRepository userAuthorityJpaRepository;
    private final  SecurityUtils securityUtils;

   public UserRepository(UserJpaRepository userJpaRepository, UserAuthorityJpaRepository userAuthorityJpaRepository, SecurityUtils securityUtils) {
      this.userJpaRepository = userJpaRepository;
      // this.userAuthorityJpaRepository = userAuthorityJpaRepository;

       this.securityUtils = securityUtils;
   }


    List<hr.tvz.zendeli.vaxapp.security.user.User> findAll(){
        return userJpaRepository.findAll();
    }

   public Optional<User> findOneByUsername(String userName){


    //String username= securityUtils.getCurrentUserUsername().toString();

    hr.tvz.zendeli.vaxapp.security.user.User user= new hr.tvz.zendeli.vaxapp.security.user.User();
            user=  userJpaRepository.findUsersByUsername(userName);

       Authority authorityOfUser=user.getAuthorities().get(0);
    String authority =authorityOfUser.getName();
       SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
    User userSec= new User(user.getUsername(),user.getPassword(), Collections.singleton(simpleGrantedAuthority));

    return Optional.of(userSec);
   }


    public Optional<hr.tvz.zendeli.vaxapp.security.user.User> findOneByUsername2(String userName){


        //String username= securityUtils.getCurrentUserUsername().toString();

       /* hr.tvz.zendeli.vaxapp.security.user.User user= new hr.tvz.zendeli.vaxapp.security.user.User();
        user=  userJpaRepository.findUsersByUsername(userName);

        Authority authorityOfUser=user.getAuthorities().get(0);
        String authority =authorityOfUser.getName();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        User userSec= new User(user.getUsername(),user.getPassword(), Collections.singleton(simpleGrantedAuthority));*/

        String currentUserName =securityUtils.getCurrentUserUsername().get();

        hr.tvz.zendeli.vaxapp.security.user.User user =userJpaRepository.findUsersByUsername(currentUserName);
        Authority authorityOfUser=user.getAuthorities().get(0);
        String authority =authorityOfUser.getName();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);




        return Optional.of(user);
    }


}
