package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddEventCommand extends Command {
    public AddEventCommand(TaskList taskList, String userInput) {
        taskList.addEventTask(userInput);
        Ui.printAddSuccess(taskList);
    }
}
