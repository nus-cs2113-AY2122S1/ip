package duke.command;

import duke.exception.DukeException;
import duke.storage.TaskList;
import duke.ui.Ui;

public class FindTaskOfDateCommand extends Command {

    private final String inputDate;

    public FindTaskOfDateCommand(TaskList taskList, Ui ui, String inputDate) {
        super(taskList, ui);
        this.inputDate = inputDate;
    }

    @Override
    public void execute() throws DukeException {
        int taskNumber = 1;
        String tasksWithSameDate = "";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTask(i).getDate().equals(inputDate)){
                tasksWithSameDate = tasksWithSameDate.concat(taskNumber + "."+ taskList.getTask(i).toString() + "\n");
                taskNumber++;
            }
        }
        Ui.printWithLine(tasksWithSameDate);
    }
}
