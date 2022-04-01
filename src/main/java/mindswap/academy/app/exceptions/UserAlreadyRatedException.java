package mindswap.academy.app.exceptions;

public class UserAlreadyRatedException extends RuntimeException {
    public UserAlreadyRatedException(String username, String title) {
        super("User " + username + " already rated " + title);
    }
}
