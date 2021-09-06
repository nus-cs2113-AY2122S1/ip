package unker.command;

import unker.task.ToDo;
import unker.ui.UI;
import unker.Unker;

/**
 * Command to add a new {@link unker.task.ToDo} into the task manager {@link unker.Unker}. 
 *
 * Usage in UI: todo description
 */
public class ToDoCommand extends Command {

    public ToDoCommand() {
        super("todo", "todo <description>");
    }

    @Override
    public void execute(UI ui, Unker unker, String data) {
        ToDo toDo = new ToDo(data);
        unker.addTask(toDo);
        ui.printSection(ADDED_TASK_MESSAGE, "\t" + toDo);
    }
}
