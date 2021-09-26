package duke.command;

import duke.exception.EmptyTasklistException;
import duke.task.TaskManager;

public class ListCommand extends Command{

    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            dukeMessage = taskManager.getTasklistEntries();
        } catch (EmptyTasklistException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, false, false);
    }

}
