package mindswap.academy.app.persistance.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalNews {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 10000)
    private String description;

    private String url;

    private String urlToImage;

    private Date publishedAt;

    private String author;

    private String language;

    private String country;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(cascade = CascadeType.MERGE)
    private Rating rating;
}
