package unker.command;

import unker.task.Task;
import unker.task.storage.TasksFileException;
import unker.ui.UI;
import unker.task.Unker;

/**
 * Command to mark a task in the task manager {@link Unker} as done.
 * 
 * Usage in UI: done taskNumber
 */
public class DoneCommand extends Command {

    protected DoneCommand() {
        super("done", "done <taskNumber>");
    }
    
    private Task getTask(Unker unker, String data) {
        try {
            int taskNumber = Integer.parseInt(data);
            if (taskNumber > 0 && taskNumber <= unker.getTotalTasks())  {
                return unker.getTask(taskNumber - 1);
            }
        } catch (NumberFormatException nfe) {
            // Return nothing if it is not a number.
        }
        return null;
    }

    @Override
    public void execute(UI ui, Unker unker, String data) throws InvalidCommandException, TasksFileException {
        Task task = getTask(unker, data);
        if (task == null) {
            throw new InvalidCommandException("Unker don't think that is a valid number leh.", this);
        } else if (task.isDone()) {
            ui.printSection("You finish this task already leh:", "\t" + task, "");
        } else {
            task.setDone(true);
            unker.saveData();
            ui.printSection("Good job, this task finish already:", "\t" + task, "");
        }
    }
}
