package duke.exception;

import duke.task.Task.Types;

public class WrongNumberOfArgumentsException extends Exception {
    private final static String MESSAGE = "Wrong arguments. Usage: %s <action> /%s <datetime>";

    public WrongNumberOfArgumentsException(Types type, String preposition) {
        super(String.format(MESSAGE, type.toString().toLowerCase(), preposition));
    }
}
