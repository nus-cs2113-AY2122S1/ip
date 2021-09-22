package duke.command;

import duke.exception.NoTaskFoundException;
import duke.task.TaskManager;

public class FindTaskCommand extends Command {

    public FindTaskCommand (TaskManager taskManager, String commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            dukeMessage = taskManager.findTask(commandArguments);
        } catch (NoTaskFoundException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, false, false);
    }

}
