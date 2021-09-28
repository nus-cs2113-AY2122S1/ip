package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddDeadlineCommand extends Command {
    /**
     * Add deadline task to task list
     *
     * @param taskList list of task
     * @param userInput user input
     */

    public AddDeadlineCommand(TaskList taskList, String userInput) {
        taskList.addDeadlineTask(userInput);
        Ui.printAddSuccess(taskList);
    }
}
