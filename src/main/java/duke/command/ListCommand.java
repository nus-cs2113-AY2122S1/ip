package duke.command;

import duke.tasklist.TaskList;

public class ListCommand extends Command {
    /**
     * Print list of existing tasks
     *
     * @param taskList list of task
     */
    public ListCommand(TaskList taskList) {
        taskList.printTasks();
    }
}
