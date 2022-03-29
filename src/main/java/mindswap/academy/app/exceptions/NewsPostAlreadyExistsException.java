package mindswap.academy.app.exceptions;

public class NewsPostAlreadyExistsException extends RuntimeException{
    public NewsPostAlreadyExistsException(String title) {
        super(String.format("News post with title %s already exists", title));
    }
}
