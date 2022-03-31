package mindswap.academy.app.exceptions;

public class ParametersDontMatchException extends RuntimeException {
    public ParametersDontMatchException(String username, String title) {
        super("This user doesn't have a news post with the provided title: " + username + " " + title);
    }
}
