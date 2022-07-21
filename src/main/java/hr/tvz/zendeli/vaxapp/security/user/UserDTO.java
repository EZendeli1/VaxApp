package hr.tvz.zendeli.vaxapp.security.user;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {

    Long ID;
    String username;
    String firstName;
    String lastName;
    Set<String> authorities= new HashSet<String>(); ;


    public UserDTO(Long ID, String username, String firstName, String lastName, Set<String> authorities) {
        this.ID = ID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
    }
}
