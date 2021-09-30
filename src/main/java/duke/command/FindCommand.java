package duke.command;

import duke.exception.DukeException;
import duke.storage.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private final String inputKeyword;

    /**
     * A constructor to find tasks associated to an input keyword.
     *
     * @param taskList user's task list.
     * @param ui the user interface.
     * @param inputKeyword keyword from user.
     */
    public FindCommand(TaskList taskList, Ui ui, String inputKeyword) {
        super(taskList, ui);
        this.inputKeyword = inputKeyword;
    }

    /**
     * Executes command.
     *
     * @throws DukeException exception thrown when index is out of range of 0 and last index on taskList.
     */
    @Override
    public void execute() throws DukeException {
        String tasksToPrint = TASK_FOUND;
        int taskNumber = 1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTask(i).toString().contains(inputKeyword)) {
                tasksToPrint = tasksToPrint.concat(taskNumber + "." + taskList.getTask(i).toString() + NEW_LINE);
            }
        }
        if (tasksToPrint.equals(TASK_FOUND)) {
            tasksToPrint = TASK_NOT_FOUND;
        }
        Ui.printWithLine(tasksToPrint);
    }
}
