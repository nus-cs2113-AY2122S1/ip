package commands;

public class IncorrectCommand extends Command{

    private String message;

    public IncorrectCommand(String message) {
        this.message = message;
    }

    public CommandResult execute() {
        return new CommandResult(message,PrintOptions.DEFAULT);
    }
}
