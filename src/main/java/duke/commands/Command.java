package duke.commands;

import duke.tasks.TaskList;

public abstract class Command {
    protected TaskList taskList;
    protected String userCommand;

    protected Command(TaskList taskList, String userCommand) {
        this.taskList = taskList;
        this.userCommand = userCommand;
    }

    public abstract  CommandOutput execute() throws Exception;
}
