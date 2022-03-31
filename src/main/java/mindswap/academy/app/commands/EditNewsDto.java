package mindswap.academy.app.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EditNewsDto {

    private String title;
    private String content;
    private String imageURL;

}
