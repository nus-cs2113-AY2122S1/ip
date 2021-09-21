package duke.command;

import duke.exception.DeadlineFormatException;
import duke.task.TaskManager;

public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(TaskManager taskManager, String commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            taskManager.addDeadline(commandArguments);
        } catch (DeadlineFormatException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, false, false);
    }

}
