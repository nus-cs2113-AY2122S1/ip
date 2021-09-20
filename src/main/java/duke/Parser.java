package duke;

public class Parser {
    private String userInput;
    private String command;
    private String argument;

    public Parser(String userInput) {
        this.userInput = userInput;

        String[] commandAndArgument = getCommandAndArgument(userInput);
        this.command = commandAndArgument[0];
        this.argument = (commandAndArgument.length == 2) ? commandAndArgument[1] : "";
    }

    /**
     * Gets the task description and argument.
     *
     * @param argument The argument from getCommandAndArgument(<string>).
     * @param splitString The string to split at.
     * @return String array: [0] - Description, [1] - Argument Value.
     */
    public static String[] getTaskDescriptionAndArg(String argument, String splitString) {
        String[] argSplit = argument.split(splitString, 2);
        argSplit[0] = argSplit[0].trim();
        if (argSplit.length == 2) {
            argSplit[1] = argSplit[1].trim();
            return argSplit;
        }

        return new String[]{argSplit[0], ""};
    }

    /**
     * Splits user input string into command and argument.
     *
     * @param input The user input string.
     * @return String array: [0] - Command, [1] - argument.
     */
    private String[] getCommandAndArgument(String input) {
        String[] result = input.trim().split("\\s+", 2);
        if (result.length != 2) {
            return new String[]{result[0], ""};
        }

        return result;
    }

    public String getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }
}
