package duke.exception;

import duke.task.Type;

/**
 * Exception to be thrown when attempting to add a timed
 * task (deadline / event) that is formatted incorrectly.
 */
public class WrongNumberOfArgumentsException extends Exception {
    private final static String MESSAGE = "Wrong arguments. Usage: %s <action> /%s <datetime>";

    /**
     * Takes in a task type to print the proper usage of
     * the command for the corresponding timed task.
     *
     * @param type Task type that user used wrong arguments for.
     */
    public WrongNumberOfArgumentsException(Type type) {
        super(String.format(MESSAGE, type, type.PREPOSITION));
    }

}
