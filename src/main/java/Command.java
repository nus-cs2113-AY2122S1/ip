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
     * Checks if the given input is indicating that the user is attempting to run this command.
     *
     * @param inputLine Raw input line to check.
     * @return True if the user is trying to run this command.
     */
    public boolean isCommand(String inputLine) {
        String commandLine = inputLine.strip();
        return commandLine.equals(command) || commandLine.startsWith(command + " ");
    }

    /**
     * Checks if the given input is a valid command.
     *
     * @param inputLine Raw input line to check.
     * @return True if the input is valid.
     */
    public boolean isValidCommandLine(String inputLine) {
        return inputLine.strip().equals(getCommand());
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
     * Returns the parsed values from the argument.
     *
     * @param inputLine Raw input line to parse.
     * @return List of parsed values.
     */
    public String[] parseCommand(String inputLine) {
        return new String[]{""};
    }
}
