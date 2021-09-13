package duke.commandHandler;

/**
 * CommandHandling class helps to check the type of command given.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class DukeCommandHandling {
    private String commandString;

    /**
     * Constructor of CommandHandling class. Each object created
     * will consist of the attribute commandString which contain
     * the command string of the user input.
     *
     * @param commandString The raw user input command.
     */
    public DukeCommandHandling(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Check if the command is "bye"
     *
     * @return a boolean of true or false regarding the check.
     */
    public boolean isBye() {
        if (commandString.equals("bye")) {
            return true;
        }
        return false;
    }

    /**
     * Check if the command is "list"
     *
     * @return a boolean of true or false regarding the check.
     */
    public boolean isList() {
        if (commandString.equals("list")) {
            return true;
        }
        return false;
    }

    /**
     * Check if the command is "done"
     *
     * @return a boolean of true or false regarding the check.
     */
    public boolean isDone() {
        if (commandString.split(" ")[0].equals("done")) {
            return true;
        }
        return false;
    }

    /**
     * Check if the command is "todo"
     *
     * @return a boolean of true or false regarding the check.
     */
    public boolean isToDo() {
        if (commandString.split(" ")[0].equals("todo")) {
            return true;
        }
        return false;
    }

    /**
     * Check if the command is "deadline"
     *
     * @return a boolean of true or false regarding the check.
     */
    public boolean isDeadline() {
        if (commandString.split(" ")[0].equals("deadline")) {
            return true;
        }
        return false;
    }

    /**
     * Check if the command is "event"
     *
     * @return a boolean of true or false regarding the check.
     */
    public boolean isEvent() {
        if (commandString.split(" ")[0].equals("event")) {
            return true;
        }
        return false;
    }
}
