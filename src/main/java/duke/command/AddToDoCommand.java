package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddToDoCommand extends Command {
    /**
     * Add todo task to task list
     *
     * @param taskList list of task
     * @param userInput user input
     */
    public AddToDoCommand(TaskList taskList, String userInput) {
        boolean taskAddedSuccess = taskList.addToDoTask(userInput);
        if (taskAddedSuccess) {
            Ui.printAddSuccess(taskList);
        }
    }
}
