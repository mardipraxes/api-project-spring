package mindswap.academy.app.exceptions;


import mindswap.academy.app.exceptions.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> dealWithUserNotFound(Exception e, HttpServletRequest request) {
        ErrorMessage error = buildError(e, request, HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<ErrorMessage> dealWithInvalidRequest(Exception e, HttpServletRequest request) {
        ErrorMessage error = buildError(e, request, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NewsPostAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> dealWithNewsPostAlreadyExists(Exception e, HttpServletRequest request) {
        ErrorMessage error = buildError(e, request, HttpStatus.CONFLICT.toString());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = InvalidQueryException.class)
    public ResponseEntity<ErrorMessage> dealWithInvalidQuery(Exception e, HttpServletRequest request) {
        ErrorMessage error = buildError(e, request, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }




    private ErrorMessage buildError(Exception e, HttpServletRequest request, String statusCode) {
        return ErrorMessage.builder()
                .timestamp(new Date())
                .verb(request.getMethod())
                .path(request.getRequestURI())
                .statusCode(statusCode)
                .message(e.getMessage())
                .build();
    }
}
