package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(TaskList taskList, String userInput) {
        taskList.addDeadlineTask(userInput);
        Ui.printAddSuccess(taskList);
    }
}
