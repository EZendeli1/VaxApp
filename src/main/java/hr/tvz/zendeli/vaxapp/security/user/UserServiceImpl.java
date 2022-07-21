package hr.tvz.zendeli.vaxapp.security.user;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userDTOS= userRepository.findAll().stream().map(this::mapUserToDTO).collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public UserDTO findUsersByUsername(String username) {
        List<UserDTO> userDTOS=userRepository.findOneByUsername2(username).stream().map(this::mapUserToDTO).collect(Collectors.toList());
        return userDTOS.get(0);
    }




    public UserDTO mapUserToDTO(User user){

        UserDTO userDTO=new UserDTO(user.getID(),user.getUsername(),user.getFirstName(),user.getLastName(), Collections.singleton(user.getAuthorities().get(0).getName()));

        return userDTO;
    }



}
