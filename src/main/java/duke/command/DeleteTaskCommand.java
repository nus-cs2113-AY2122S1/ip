package duke.command;

import duke.exception.DeadlineFormatException;
import duke.exception.DeleteFormatException;
import duke.exception.EventFormatException;
import duke.exception.InvalidTaskIdException;
import duke.task.TaskManager;

public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(TaskManager taskManager, String commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            taskManager.deleteTask(commandArguments);
        } catch (DeleteFormatException e) {
            dukeMessage = e.toString();
        } catch (InvalidTaskIdException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, false, false);
    }
}
