package duke.command;

import duke.exception.DukeException;
import duke.storage.TaskList;
import duke.ui.Ui;

public class FindTaskOfDateCommand extends Command {

    private final String inputDate;

    /**
     * A constructor to find tasks associated with a specific date.
     *
     * @param taskList user's task list.
     * @param ui the user interface.
     * @param inputDate specific input date from user.
     */
    public FindTaskOfDateCommand(TaskList taskList, Ui ui, String inputDate) {
        super(taskList, ui);
        this.inputDate = inputDate;
    }

    /**
     * Executes command.
     *
     * @throws DukeException exception thrown when index is out of range of 0 and last index on taskList.
     */
    @Override
    public void execute() throws DukeException {
        int taskNumber = 1;
        String tasksWithSameDate = "";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTask(i).getDate().equals(inputDate)){
                tasksWithSameDate = tasksWithSameDate.concat(taskNumber + "."+ taskList.getTask(i).toString() + NEW_LINE);
                taskNumber++;
            }
        }
        Ui.printWithLine(tasksWithSameDate);
    }
}
