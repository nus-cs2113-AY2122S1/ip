package duke.exception;

import duke.task.Task;
import duke.task.Type;

/**
 * Exception to be thrown when attempting to add a timed task (deadline / event) that is formatted incorrectly
 */
public class WrongNumberOfArgumentsException extends Exception {
    private final static String MESSAGE = "Wrong arguments. Usage: %s <action> /%s <datetime>";

    public WrongNumberOfArgumentsException(Type type) {
        super(String.format(MESSAGE, type, type.PREPOSITION));
    }

}
