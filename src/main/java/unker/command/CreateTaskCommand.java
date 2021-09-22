package unker.command;

import unker.task.Task;
import unker.task.Unker;
import unker.ui.UI;

public abstract class CreateTaskCommand extends Command {

    protected CreateTaskCommand(String name, String format) {
        super(name, format);
    }
    
    protected void addTask(UI ui, Unker unker, Task task) throws InvalidCommandException {
        if (task == null) {
            throw new InvalidCommandException(INVALID_FORMAT_MESSAGE, this);
        }
        unker.addTask(task);
        unker.saveData();
        ui.printSection(ADDED_TASK_MESSAGE, "\t" + task);
    }
    
}
