package mindswap.academy.app.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsJsonDto {

    @JsonProperty("data")
    private NewsFindDto[] data;

    @JsonIgnore
    private Object pagination;
}
