package duke.command;

public class ByeCommand extends Command {

    final public static String COMMAND_WORD = "bye";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + TAG_NO_FORMAT;

    public static boolean isExit(Command command) {
        return command instanceof ByeCommand;
    }

    @Override
    public void execute() {

    }
}
