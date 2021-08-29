package shikabot.command;

public class InvalidCommand extends Command {

    public void execute() {
        ui.printUnknownCommandMessage();
    }
}
