package hr.tvz.zendeli.vaxapp.security.user;

import hr.tvz.zendeli.vaxapp.security.authority.Authority;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @Column(name="ID")
    @GeneratedValue
    Long ID;

    @Column(name="username")
    String username;

    @Column(name="first_Name")
    String firstName;

    @Column(name="last_Name")
    String lastName;

    @Column(name="password")
    String password;

    @ToString.Exclude
    @ManyToMany(targetEntity = Authority.class,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")}
    )
    private List<Authority> authorities =new ArrayList<Authority>();

    public User() {
    }


}
