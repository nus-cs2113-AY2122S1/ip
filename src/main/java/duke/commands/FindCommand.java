package duke.commands;

import duke.tasks.TaskList;

public class FindCommand extends Command {

    public FindCommand(TaskList taskList, String userCommand){
        super(taskList, userCommand);
    }

    @Override
    public CommandOutput execute() throws Exception{
        String response = taskList.findTask(userCommand);

        return new CommandOutput(response, false);
    }
}
