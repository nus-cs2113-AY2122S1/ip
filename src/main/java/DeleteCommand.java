public class DeleteCommand extends Command {
    private int deleteTaskNumber;

    public DeleteCommand(int deleteTaskNumber) {
        this.deleteTaskNumber = deleteTaskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage dukeStorage) {
        try {
            Task taskToDelete = taskList.deleteTask(deleteTaskNumber);
            ui.deleteTaskMessage(taskToDelete);
            dukeStorage.updateStorage(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskNumberProvided();
        }
    }

    @Override
    public boolean exit() {
        return false;
    }
}
