package mindswap.academy.app.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@Table(name = "category")
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    private Collection<ExternalNews> externalNews;
    @ManyToMany(mappedBy = "categories")
    private Collection<NewsPost> newsPost;


}
