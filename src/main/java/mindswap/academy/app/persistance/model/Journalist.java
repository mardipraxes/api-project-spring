package mindswap.academy.app.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Journalist extends User {

    @Column
    @OneToMany(mappedBy = "journalist")
    private Collection<NewsPost> newsPosts;

}
