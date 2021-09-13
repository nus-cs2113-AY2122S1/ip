package duke.commandHandler;

public class SaveCommandHandling {
    private String commandString;

    /**
     * Constructor of CommandHandling class. Each object created
     * will consist of the attribute commandString which contain
     * the command string of the user input.
     *
     * @param commandString The raw user input command.
     */
    public SaveCommandHandling(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Check if the command is "todo"
     *
     * @return a boolean of true or false regarding the check.
     */
    public boolean isToDo() {
        if (commandString.split("-/-")[0].equals("t")) {
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
        if (commandString.split("-/-")[0].equals("d")) {
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
        if (commandString.split("-/-")[0].equals("e")) {
            return true;
        }
        return false;
    }
}
