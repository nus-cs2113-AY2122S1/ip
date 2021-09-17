package duke.command;

/**
 * The Command class handles the validation of a supported command and printing of its usage.
 */
public class Command {

    /* The supported command */
    private String command;

    /**
     * Instantiates a new supported command.
     *
     * @param command Command to support.
     */
    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    /**
     * Returns the command syntax of the command.
     *
     * @return Command syntax.
     */
    public String getUsage() {
        return String.format("%s", command);
    }

    /**
     * Checks if the given input is indicating that the user is attempting to run this command.
     *
     * @param inputLine Raw input line to check.
     * @return True if the user is trying to run this command.
     */
    public boolean isCommand(String inputLine) {
        String strippedLine = inputLine.strip();
        boolean isExactCommand = strippedLine.equals(command);
        boolean isStartWithCommand = strippedLine.startsWith(command + " ");
        return isExactCommand || isStartWithCommand;
    }

    /**
     * Checks if the given input is a valid command.
     *
     * @param inputLine Raw input line to check.
     * @return True if the input is valid.
     */
    public boolean isValidCommandLine(String inputLine) {
        String strippedLine = inputLine.strip();
        return strippedLine.equals(command);
    }

    /**
     * Returns the parsed values (if any) from the given command line.
     *
     * @param inputLine Raw input line to parse.
     * @return List of parsed values.
     */
    public String[] extractCommandLineValues(String inputLine) {
        return new String[] {};
    }
}
