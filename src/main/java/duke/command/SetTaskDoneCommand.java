package duke.command;

import duke.exception.DoneFormatException;
import duke.exception.InvalidTaskIdException;
import duke.exception.TaskAlreadyDoneException;
import duke.task.TaskManager;

public class SetTaskDoneCommand extends Command {

    public SetTaskDoneCommand(TaskManager taskManager, String commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            taskManager.setTaskComplete(commandArguments);
        } catch (DoneFormatException e) {
            dukeMessage = e.toString();
        } catch (InvalidTaskIdException e) {
            dukeMessage = e.toString();
        } catch (TaskAlreadyDoneException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, false, false);
    }

}
