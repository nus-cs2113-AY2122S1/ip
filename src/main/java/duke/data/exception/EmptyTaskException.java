package duke.data.exception;

public class EmptyTaskException extends DukeException {
    public EmptyTaskException() {
        super("The description of your task cannot be empty.");
    }

    public EmptyTaskException(String message) {
        super("The description of your task cannot be empty." + message);
    }
}

