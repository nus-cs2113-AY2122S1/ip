package duke.command;

/**
 * DukeException is the super class of all possible exceptions that might happen during the execution of Duke
 */
public class DukeException extends Exception{
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

