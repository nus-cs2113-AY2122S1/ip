package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddEventCommand extends Command {
    /**
     * Add event task to task list
     *
     * @param taskList list of task
     * @param userInput user input
     */
    public AddEventCommand(TaskList taskList, String userInput) {
        boolean taskAddedSuccess = taskList.addEventTask(userInput);
        if (taskAddedSuccess) {
            Ui.printAddSuccess(taskList);
        }
    }
}
