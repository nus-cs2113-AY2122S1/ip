package shikabot.command;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Function that deletes the specified task from tasklist.
     */
    public void execute() {
        try {
            ui.printDeleteTaskMessage(taskList, index);
            taskList.remove(index);
            ui.printTaskCount(taskList.size());
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskMessage();
        }
    }
}
