package duke.logic.commands;

/**
 * This Command is used to return incorrect command format messages.
 * When executed, it returns CommandResult with corresponding error.
 */
public class IncorrectCommand extends Command {
    private String errorMessage;

    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(this.errorMessage);
    }
}
