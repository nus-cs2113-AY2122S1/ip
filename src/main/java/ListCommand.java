public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage dukeStorage) {
        ui.getListMessage();
        taskList.getAllTask();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
