package Command;

import Tasks.TaskList;
public class ListCommand extends UserCommand {
    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public void execute() {
        System.out.println("     Here are the tasks in your list:");
        this.tasks.listTasks();
    }
}
