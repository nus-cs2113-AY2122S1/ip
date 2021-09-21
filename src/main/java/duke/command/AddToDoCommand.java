package duke.command;

import duke.exception.TodoFormatException;
import duke.task.TaskManager;

public class AddToDoCommand extends Command {

    public AddToDoCommand(TaskManager taskManager, String commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            taskManager.addToDo(commandArguments);
        } catch (TodoFormatException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, true, false);
    }

}
