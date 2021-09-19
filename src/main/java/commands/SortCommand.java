package commands;

import tasks.TaskList;

public class SortCommand extends UserCommand{
    public SortCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String execute() {
        String result = "     Here are the tasks in your list after sorting:\n";
        result += this.tasks.sort();
        return result;
    }
}
