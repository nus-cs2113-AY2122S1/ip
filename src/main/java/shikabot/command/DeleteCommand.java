package shikabot.command;

import shikabot.ui.TextUi;

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
            TextUi.printDeleteTaskMessage(taskList, index);
            taskList.removeTask(index);
            TextUi.printTaskCount(taskList.getSize());
        } catch (IndexOutOfBoundsException e) {
            TextUi.printInvalidTaskMessage();
        }
    }
}
