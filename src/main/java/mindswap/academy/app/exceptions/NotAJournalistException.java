package mindswap.academy.app.exceptions;

public class NotAJournalistException extends RuntimeException {
    public NotAJournalistException(String username) {
        super("The user " + username + " is not a journalist");
    }
}
