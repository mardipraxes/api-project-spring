package mindswap.academy.app.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDto {

    private String username;
    private String email;
    private String country;
    private String type;
}

