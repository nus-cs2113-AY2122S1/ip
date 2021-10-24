package duke.commands;

import duke.tasks.TaskList;

public class DoneCommand extends Command {

    public DoneCommand(TaskList taskList, String userCommand) {
        super(taskList, userCommand);
    }

    @Override
    public CommandOutput execute() throws Exception {
        String response = taskList.taskDone(userCommand);
        return new CommandOutput(response, true, taskList);
    }
}
