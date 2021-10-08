package duke.logic.commands;

/**
 *  Represents any incorrect command that has been parsed from user input.
 *  Contains a String of message that describes the error in parsing.
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
