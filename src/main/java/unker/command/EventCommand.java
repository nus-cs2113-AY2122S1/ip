package unker.command;

import unker.task.TaskFactory;
import unker.task.Unker;
import unker.ui.UI;

/**
 * Command to add a new {@link unker.task.Event} into the task manager Unker. 
 *
 * Usage in UI: event description /at time 
 */
public class EventCommand extends CreateTaskCommand {

    public EventCommand() {
        super("event", "event <description> /at <time>");
    }
    
    @Override
    public void execute(UI ui, Unker unker, String data) throws InvalidCommandException {
        addTask(ui, unker, TaskFactory.createEventTask(data));
    }
}
