package mindswap.academy.app.exceptions.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Builder
public class ErrorMessage {

    private String message;
    private String verb;
    private String path;
    private Date timestamp;
    private String statusCode;

}
