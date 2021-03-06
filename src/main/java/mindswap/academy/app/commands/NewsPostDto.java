package mindswap.academy.app.commands;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsPostDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String author;
    @NotNull
    private String[] categories;

    private String imageURL;

    private Date publishedDate;


}
