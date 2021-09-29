package unker.command;

import unker.task.TaskFactory;
import unker.task.Unker;
import unker.task.storage.TasksFileException;
import unker.ui.UI;

/**
 * Command to add a new {@link unker.task.ToDo} into the task manager {@link Unker}. 
 *
 * Usage in UI: todo description
 */
public class ToDoCommand extends CreateTaskCommand {

    public ToDoCommand() {
        super("todo", "todo <description>");
    }

    @Override
    public void execute(UI ui, Unker unker, String data) throws InvalidCommandException, TasksFileException {
        addTask(ui, unker, TaskFactory.createToDoTask(data));
    }
}
