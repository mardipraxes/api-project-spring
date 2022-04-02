package mindswap.academy.app.commands;

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
    private Date published_at;
    private String url;
    private String image;
    private String source;
    private String country;
    private String category;
    private String language;

}
