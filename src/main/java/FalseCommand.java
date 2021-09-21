

public class FalseCommand extends Command {

    public final String feedback;

    public FalseCommand(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(feedback);
    }

}
