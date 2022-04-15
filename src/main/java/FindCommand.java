public class FindCommand extends Command {
    private String taskToFind;
    public FindCommand(String taskToFind) {
        this.taskToFind = taskToFind;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage dukeStorage) {
        ui.getFindMessage();
        taskList.findTask(taskToFind);
    }

    @Override
    public boolean exit() {
        return false;
    }
}
