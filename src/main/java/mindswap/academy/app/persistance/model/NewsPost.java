package mindswap.academy.app.persistance.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@Table(name = "news_post")
@NoArgsConstructor
public class NewsPost {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Journalist journalist;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "news_categories", joinColumns =
    @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Collection<Category> categories;

    @Column
    private Date publishedDate;


    @OneToOne(mappedBy = "news", cascade = CascadeType.ALL)
    private Rating rating;

    @Column
    private String imageURL;
}
