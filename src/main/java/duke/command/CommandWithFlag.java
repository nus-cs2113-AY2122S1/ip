package duke.command;

import java.util.Arrays;

/**
 * The CommandWithFlag class extends the CommandWithArgument class by allowing the supported command to have an
 * additional flag.
 */
public class CommandWithFlag extends CommandWithArgument {

    /* Used to denote a flag */
    private final static String FLAG_PREFIX = "/";
    /* Default index if element cannot be found */
    private final static int NOT_FOUND = -1;

    /* Option to indicate the flag on the command line */
    private String flagOption;

    /* Name of the flag */
    private String flagName;

    /**
     * Instantiates a new supported command that has an argument and a flag.
     *
     * @param command      Command to support.
     * @param argumentName Name of the argument.
     * @param flagOption   Option to indicate the flag on the command line.
     * @param flagName     Name of the flag.
     */
    public CommandWithFlag(String command, String argumentName, String flagOption, String flagName) {
        super(command, argumentName);
        this.flagOption = flagOption;
        this.flagName = flagName;
    }

    /**
     * Returns the command syntax of the command.
     *
     * @return Command syntax.
     */
    @Override
    public String getUsage() {
        return String.format("%s <%s> %s%s <%s>", super.getCommand(), super.getArgumentName(), FLAG_PREFIX, flagOption,
                flagName);
    }

    /**
     * Checks if the given input is a valid command.
     *
     * @param inputLine Raw input line to check.
     * @return True if the input is valid.
     */
    @Override
    public boolean isValidCommandLine(String inputLine) {
        String commandLine = inputLine.strip();
        String[] tokens = commandLine.split(" ", -1);
        int flagIndex = findFlagIndex(tokens);

        if (flagIndex == NOT_FOUND) {
            return false;
        }

        String argumentValue = String.join(" ", Arrays.copyOfRange(tokens, 1, flagIndex));
        String flagValue = String.join(" ", Arrays.copyOfRange(tokens, flagIndex + 1, tokens.length));

        boolean isStartWithCommand = commandLine.startsWith(super.getCommand() + " ");
        boolean isNonEmptyArgument = argumentValue.length() > 0;
        boolean isNonEmptyFlag = flagValue.length() > 0;

        return isStartWithCommand && isNonEmptyArgument && isNonEmptyFlag;
    }

    /**
     * Returns the parsed values from the argument.
     *
     * @param inputLine Raw input line to parse.
     * @return List of parsed values.
     */
    @Override
    public String[] extractCommandLineValues(String inputLine) {
        String commandLine = inputLine.strip();
        String[] tokens = commandLine.split(" ", -1);
        int flagIndex = findFlagIndex(tokens);

        String[] argumentTokens = Arrays.copyOfRange(tokens, 1, flagIndex);
        String[] flagTokens = Arrays.copyOfRange(tokens, flagIndex + 1, tokens.length);

        String argumentValue = String.join(" ", argumentTokens);
        String flagValue = String.join(" ", flagTokens);

        return new String[] {argumentValue, flagValue};
    }

    /**
     * Returns the index of element containing the flag option.
     *
     * @param tokens List of tokens to check.
     * @return Index of the flag option. NOT_FOUND if cannot be found.
     */
    private int findFlagIndex(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(FLAG_PREFIX + flagOption)) {
                return i;
            }
        }
        return NOT_FOUND;
    }
}
