package mindswap.academy.app.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsFindDto {

    private String title;
    private String description;
    private String author;
    @JsonProperty("published_at")
    private Date publishedAt;
    private String url;
    private String image;
    private String source;
    private String country;
    private String category;
    private String language;

}
