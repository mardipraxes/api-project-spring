package mindswap.academy.app.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Journalist extends User {

    @Column
    @OneToMany(mappedBy = "journalist", cascade = CascadeType.ALL)
    private Collection<NewsPost> newsPosts;

}
