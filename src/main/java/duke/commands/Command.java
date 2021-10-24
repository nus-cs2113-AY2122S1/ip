package duke.commands;

import duke.tasks.TaskList;

public abstract class Command {

    protected TaskList taskList;
    protected String userCommand;

    protected Command(TaskList taskList, String userCommand) {
        this.taskList = taskList;
        this.userCommand = userCommand;
    }

    //attempts to execute program according to user command
    public abstract  CommandOutput execute() throws Exception;
}
