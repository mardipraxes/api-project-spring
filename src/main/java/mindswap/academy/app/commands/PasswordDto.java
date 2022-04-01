package mindswap.academy.app.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class PasswordDto {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

}
