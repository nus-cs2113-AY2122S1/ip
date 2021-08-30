/**
 * This class refactors the user command and
 * provides validation.
 *
 * @author richwill28
 */
class Command {
    /** User command */
    String command;

    /**
     * The constructor method. Initializes user command.
     *
     * @param command User command.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Returns user command.
     *
     * @return User command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Checks whether command is of the type "bye".
     *
     * @return true if the command matches, otherwise false.
     */
    public boolean isBye() {
        return command.equals("bye");
    }

    /**
     * Checks whether command is of the type "list".
     *
     * @return true if the command matches, otherwise false.
     */
    public boolean isList() {
        return command.equals("list");
    }

    /**
     * Checks whether command is of the type "done".
     *
     * @return true if the command matches, otherwise false.
     */
    public boolean isDone() {
        return command.split(" ")[0].equals("done");
    }

    /**
     * Checks whether command is of the type "todo".
     *
     * @return true if the command matches, otherwise false.
     */
    public boolean isTodo() {
        return command.split(" ")[0].equals("todo");
    }

    /**
     * Checks whether command is of the type "deadline".
     *
     * @return true if the command matches, otherwise false.
     */
    public boolean isDeadline() {
        return command.split(" ")[0].equals("deadline");
    }

    /**
     * Checks whether command is of the type "event".
     *
     * @return true if the command matches, otherwise false.
     */
    public boolean isEvent() {
        return command.split(" ")[0].equals("event");
    }
}
