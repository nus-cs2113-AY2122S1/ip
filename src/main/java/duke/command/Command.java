package duke.command;

import duke.task.TaskManager;

public abstract class Command {

    public TaskManager taskManager;
    public String commandArguments;

    public Command() {

    }

    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public Command(TaskManager taskManager, String commandArguments) {
        this.taskManager = taskManager;
        this.commandArguments = commandArguments;
    }

    public abstract CommandResult executeCommand() throws Exception;

}
