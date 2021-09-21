package duke.command;

import duke.exception.EventFormatException;
import duke.task.TaskManager;

public class AddEventCommand extends Command {

    public AddEventCommand(TaskManager taskManager, String commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            taskManager.addEvent(commandArguments);
        } catch (EventFormatException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, true, false);
    }
}
