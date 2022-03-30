package mindswap.academy.app.exceptions;

public class NewsNotFoundException extends RuntimeException {
    public NewsNotFoundException() {
        super("No news found");
    }
}
