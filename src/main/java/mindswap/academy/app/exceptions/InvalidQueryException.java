package mindswap.academy.app.exceptions;

public class InvalidQueryException extends RuntimeException {
    public InvalidQueryException() {
        super("Please enter a valid query");
    }
}
