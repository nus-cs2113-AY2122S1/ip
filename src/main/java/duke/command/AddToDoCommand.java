package duke.command;

import duke.exception.ToDoFormatException;
import duke.task.TaskManager;

public class AddToDoCommand extends Command {

    public AddToDoCommand(TaskManager taskManager, String commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    public CommandResult executeCommand() {

        String dukeMessage = "";

        try {
            dukeMessage = taskManager.addToDo(commandArguments);
        } catch (ToDoFormatException e) {
            dukeMessage = e.toString();
        }

        return new CommandResult(taskManager, dukeMessage, true, false);
    }

}
