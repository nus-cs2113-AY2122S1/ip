package duke.TaskList.command;

import duke.TaskList.TaskManager;

public class Command {
    protected TaskManager taskManager;
    protected String component;

    public Command(TaskManager taskManager, String component) {
        this.taskManager = taskManager;
        this.component = component;
    }

    public void execute() {}
}
