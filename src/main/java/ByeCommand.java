/**
 * Command to run to exit from the software.
 * 
 * Usage in UI: bye
 */
public class ByeCommand extends Command {

    protected ByeCommand() {
        super("bye");
    }

    @Override
    public void execute(UI ui, Unker unker, String data) {
        ui.printByeMessage();
        System.exit(0);
    }
}
