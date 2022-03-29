package mindswap.academy.app.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String id) {
        super(String.format("User not found with id %s", id));
    }
}
