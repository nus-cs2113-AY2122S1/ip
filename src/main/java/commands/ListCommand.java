package commands;

import tasks.TaskList;


/**
 * A class to handle commands of listing all the tasks inside the task list.
 */
public class ListCommand extends UserCommand {
    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String execute() {
        String result = "     Here are the tasks in your list:\n";
        result += this.tasks.listTasks();
        return result;
    }
}
