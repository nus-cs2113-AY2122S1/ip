package unker.command;

import unker.task.Deadline;
import unker.task.TaskFactory;
import unker.task.Unker;
import unker.ui.UI;

/**
 * Command to add a new {@link Deadline} into the task manager Unker. 
 *
 * Usage in UI: deadline description /by YYYY-MM-DD HH:mm 
 */
public class DeadlineCommand extends CreateTaskCommand {

    public DeadlineCommand() {
        super("deadline", "deadline <description> /by YYYY-MM-DD HH:mm");
    }
    
    @Override
    public void execute(UI ui, Unker unker, String data) throws InvalidCommandException {
        addTask(ui, unker, TaskFactory.createDeadlineTask(data));
    }
}
