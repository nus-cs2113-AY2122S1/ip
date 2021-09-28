package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddToDoCommand extends Command {
    public AddToDoCommand(TaskList taskList, String userInput) {
        taskList.addToDoTask(userInput);
        Ui.printAddSuccess(taskList);
    }
}
