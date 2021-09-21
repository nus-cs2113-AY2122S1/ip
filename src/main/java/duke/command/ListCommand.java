package duke.command;

import duke.storage.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * A constructor to list out all tasks in taskList.
     *
     * @param taskList user's task list.
     * @param ui the user interface.
     */
    public ListCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        Ui.printWithLine(LIST_MESSAGE + taskList.getList());
    }
}
