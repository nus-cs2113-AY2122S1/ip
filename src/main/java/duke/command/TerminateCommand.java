package duke.command;

public class TerminateCommand extends Command {

    private final String EXIT_MSG = "=> Come back soon, I'm still hungry \uD83D\uDE0B";

    @Override
    public CommandResult executeCommand() {
        return new CommandResult(EXIT_MSG, false, true);
    }

}
