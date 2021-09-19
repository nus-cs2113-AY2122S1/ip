package shikabot.command;

public class InvalidCommand extends Command {

    /**
     * Function that prints invalid command message.
     */
    public void execute() {
        ui.printUnknownCommandMessage();
    }
}
