package duke.command;

public class ExitCommand implements Command {
    private static final CommandType type = CommandType.EXIT;

    /**
     * Exit command constructor
     *
     * @param printMessage Print message to user on executing command
     */
    @Override
    public void run(boolean printMessage) {
        System.out.println("Exited Duke. See you later.");
    }

    /**
     * @return Type of command
     */
    @Override
    public CommandType getType() {
        return type;
    }
}
