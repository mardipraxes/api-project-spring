package mindswap.academy.app.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@Table(name = "roles_api")
@NoArgsConstructor
public class Role {

    @javax.persistence.Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "role_name")
    private String name;
    @Column(name = "users_id")
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    public Role(String name) {
        this.name = name;
    }
}
