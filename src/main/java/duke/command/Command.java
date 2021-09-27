package duke.command;

/**
 * Abstract Class to represent a Command.
 * <code>name</code> corresponds to name of command.
 * <code>usage</code> corresponds to the mesasge that would be appended to <code>name</code> when displaying proper usage.
 * <code>argument</code> corresponds to argument entered by user when using command.
 */
public abstract class Command {
    private static final String USAGE_MESSAGE = "Wrong argument(s). Usage: ";

    private final String name;
    private final String usage;
    final String argument;

    Command(String name, String usage, String argument){
        this.name = name;
        this.usage = usage;
        this.argument = argument;
    }

    /**
     * Returns usage message
     * @return the usage message followed by the name of te command and anything to be appended after in <code>usage</code>.
     */
    String getUsage() {
        return USAGE_MESSAGE + name + usage;
    }

    @Override
    public String toString(){
        return name;
    }

    /**
     * Returns boolean on whether the command argument is valid
     * @return boolean command is valid
     */
    abstract boolean isValid();

    /**
     * code to be executed by function and returns a
     * boolean on whether the program should continue running.
     * @return boolean program should continue running
     */
    abstract boolean execute();
}
