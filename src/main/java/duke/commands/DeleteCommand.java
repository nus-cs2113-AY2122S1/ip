package duke.commands;

import duke.tasks.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand(TaskList taskList, String userCommand) {
        super(taskList, userCommand);
    }

    @Override
    public CommandOutput execute() throws Exception {
        String response = taskList.deleteTask(userCommand);
        return new CommandOutput(response, true, taskList);
    }
}
