package duke.command;

import duke.exception.DukeException;
import duke.storage.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private final String inputKeyword;

    public FindCommand(TaskList taskList, Ui ui, String inputKeyword) {
        super(taskList, ui);
        this.inputKeyword = inputKeyword;
    }

    @Override
    public void execute() throws DukeException {
        String tasksToPrint = "";
        int taskNumber = 1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTask(i).toString().contains(inputKeyword)) {
                tasksToPrint = tasksToPrint.concat(taskNumber + "." + taskList.getTask(i).toString() + "\n");
            }
        }
        Ui.printWithLine(tasksToPrint);
    }
}
