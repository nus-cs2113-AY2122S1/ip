package duke.command;

import duke.tasklist.TaskList;

public class ListCommand extends Command {
    public ListCommand(TaskList taskList) {
        taskList.printTasks();
    }
}
