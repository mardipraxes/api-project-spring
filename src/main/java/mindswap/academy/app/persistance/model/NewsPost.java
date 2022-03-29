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

    @JoinColumn
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Category> category;

    @Column
    private Date publishedDate;

    @Column
    @OneToMany(fetch = FetchType.LAZY)
    private Rating rating;

    @Column
    private String imageURL;
}
