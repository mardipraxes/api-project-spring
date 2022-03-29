package mindswap.academy.app.persistance.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@Table(name = "ratings")
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    private Long id;

    @Column(name = "truthfulness")
    private Integer truthfulness;

    @Column(name = "writing_quality")
    private Integer writingQuality;

    @Column(name = "biased_rating")
    private Integer biasedRating;

    @JoinColumn(name = "news_id")
    @ManyToOne
    private NewsPost news;

}
