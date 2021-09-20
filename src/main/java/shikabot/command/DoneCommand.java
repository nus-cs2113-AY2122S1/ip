package shikabot.command;

import shikabot.ui.TextUi;

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
            taskList.getTask(index).markAsDone();
            TextUi.printDoneTaskMessage(taskList, index);
        } catch (IndexOutOfBoundsException e) {
            TextUi.printInvalidTaskMessage();
        }
    }
}
