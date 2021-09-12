package shikabot.command;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    public void execute() {
        ui.printExitMessage();
    }

}
