package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    /**
     * Print list of existing tasks
     *
     * @param taskList list of task
     */
    public ListCommand(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        Ui.printTasks(taskList.getTasks());
    }
}
