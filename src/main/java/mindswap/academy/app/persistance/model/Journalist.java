package mindswap.academy.app.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Journalist extends User {

    @Column
    @OneToMany(mappedBy = "journalist")
    private Collection<NewsPost> newsPosts;

}
