package herrekt.exceptions;

public class InvalidTodoException extends InvalidTaskException {
    public InvalidTodoException(String message) {
        super(message);
    }
}
