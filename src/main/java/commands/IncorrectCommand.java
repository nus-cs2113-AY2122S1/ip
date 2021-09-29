package commands;

/**
 * Represents an incorrect command.
 */
public class IncorrectCommand extends Command {

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
    }

}
