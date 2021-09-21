package duke.command;

import duke.exception.DeleteFormatException;
import duke.exception.EmptyTasklistException;
import duke.exception.InvalidTaskIdException;
import duke.task.TaskManager;

public class ListCommand extends Command{

    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            taskManager.getTasklist();
        } catch (EmptyTasklistException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, false, false);
    }

}
