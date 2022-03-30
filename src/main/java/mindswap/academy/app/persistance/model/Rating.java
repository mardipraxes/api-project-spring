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

    @Column(columnDefinition = "int(255) default 0")
    private Integer counter;

    @Column(name = "truthfulness")
    private Integer truthfulness;

    @Column(name = "writing_quality")
    private Integer writingQuality;

    @Column(name = "biased_rating")
    private Integer biasedRating;

    @OneToOne
    private ExternalNews externalNews;
    @OneToOne
    private NewsPost news;

}
