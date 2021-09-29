package duke.TaskList.command;

import duke.TaskList.TaskManager;

public class Command {
    protected TaskManager taskManager;

    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void execute() {}
}
