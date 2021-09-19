package shikabot.command;

public class DoneCommand extends Command {

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Function that marks the specified task as done.
     */
    public void execute() {
        try {
            taskList.get(index).markAsDone();
            ui.printDoneTaskMessage(taskList, index);
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidTaskMessage();
        }
    }
}
