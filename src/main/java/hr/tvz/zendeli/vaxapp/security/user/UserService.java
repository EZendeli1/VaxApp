package hr.tvz.zendeli.vaxapp.security.user;


import java.util.List;

public interface UserService {

    List<UserDTO> findAll();
    UserDTO findUsersByUsername(String username);
}
