package mindswap.academy.app.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class JournalistApplicationDto {
    @NotBlank
    @Size(min = 6, max = 20)
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String country;
}
