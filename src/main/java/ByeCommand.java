public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage dukeStorage) {
        ui.goodbyeMessage();
    }

    @Override
    public boolean exit() {
        return true;
    }
}
