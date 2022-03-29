package mindswap.academy.app.exceptions;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String request){
        super(String.format("Invalid request: %s", request));
    }
}
