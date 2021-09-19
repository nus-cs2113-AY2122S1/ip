package duke;

import duke.exceptionHandler.DukeException;

/**
 * CommandHandling class helps to check the type of command given.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class Parser {
    private String commandString;

    /**
     * Constructor of CommandHandling class. Each object created
     * will consist of the attribute commandString which contain
     * the command string of the user input.
     *
     * @param commandString The raw user input command.
     */
    public Parser(String commandString) {
        this.commandString = commandString;
    }

    public boolean isBye() {
        if (commandString.equals("bye")) {
            return true;
        }
        return false;
    }

    public boolean isList() {
        if (commandString.equals("list")) {
            return true;
        }
        return false;
    }

    public boolean isDone() {
        if (commandString.split(" ")[0].equals("done")) {
            return true;
        }
        return false;
    }

    public boolean isDelete() {
        if (commandString.split(" ")[0].equals("delete")) {
            return true;
        }
        return false;
    }

    public boolean isToDo() {
        if (commandString.split(" ")[0].equals("todo")) {
            return true;
        }
        return false;
    }

    public boolean isDeadline() {
        if (commandString.split(" ")[0].equals("deadline")) {
            return true;
        }
        return false;
    }

    public boolean isEvent() {
        if (commandString.split(" ")[0].equals("event")) {
            return true;
        }
        return false;
    }
}
