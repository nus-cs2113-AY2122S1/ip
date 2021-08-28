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
