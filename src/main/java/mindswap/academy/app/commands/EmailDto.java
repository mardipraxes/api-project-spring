package mindswap.academy.app.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmailDto {

    private String password;
    private String email;
    private String newEmail;

}
