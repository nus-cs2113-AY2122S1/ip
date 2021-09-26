package duke.commands;

import duke.tasks.TaskList;

public class NoCommand extends Command {
    public NoCommand(TaskList taskList) {
        super(taskList, "");
    }

    @Override
    public CommandOutput execute() throws Exception {
        taskList.noResponse();
        return new CommandOutput("", false, taskList);
    }
}
