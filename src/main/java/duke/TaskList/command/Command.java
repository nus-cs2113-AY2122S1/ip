package duke.TaskList.command;

import duke.TaskList.TaskManager;

public class Command {
    protected TaskManager taskManager;
    protected String components;

    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void execute() {}
}
