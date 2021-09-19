package duke.commands;

/** Includes the operations needed to exit from <code>Duke</code> */
public class ExitCommand extends Command {

    private static final String EXIT_MESSAGE = "Bye! Have a great day ahead and see you again soon.";

    /** Constructed when the command word of the user input is "bye". */
    public ExitCommand() {
        super();
    }

    @Override
    public CommandResult executeCommand() {
        CommandResult result = new CommandResult(EXIT_MESSAGE);
        return result;
    }
}
