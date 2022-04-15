public class DoneCommand extends Command {
    private int doneTaskNumber;

    public DoneCommand(int doneTaskNumber) {
        this.doneTaskNumber = doneTaskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage dukeStorage) {
        try {
            Task markTask = taskList.markTask(doneTaskNumber);
            ui.markTaskDoneMessage(markTask);
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
