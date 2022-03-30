package mindswap.academy.app.commands;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingDto {

    private Integer truthfulness;
    private Integer writingQuality;
    private Integer biasedRating;

}
