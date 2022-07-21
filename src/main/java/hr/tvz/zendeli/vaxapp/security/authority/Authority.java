package hr.tvz.zendeli.vaxapp.security.authority;

import hr.tvz.zendeli.vaxapp.security.user.User;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "authority")
public class Authority implements Serializable {


    @Id
    @Column(name="ID")
    @GeneratedValue
    Long ID;

    @Column(name="name")
    String name;

    @ToString.Exclude
    @ManyToMany(targetEntity = User.class, mappedBy = "authorities",fetch = FetchType.EAGER)
    private List<User> users =new ArrayList<User>();

    public Authority() {
    }
}
