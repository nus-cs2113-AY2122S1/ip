/**
 * Command to mark a task in the task manager Unker as done.
 * 
 * Usage in UI: done taskNumber
 */
public class DoneCommand extends Command {

    protected DoneCommand() {
        super("done");
    }
    
    private Task getTask(Unker unker, String data) {
        try {
            int taskNo = Integer.parseInt(data);
            if (taskNo > 0 && taskNo <= unker.getTotalTasks())  {
                return unker.getTask(taskNo - 1);
            }
        } catch (NumberFormatException nfe) {
            // Return nothing if it is not a number.
        }
        return null;
    }

    @Override
    public void execute(UI ui, Unker unker, String data) {
        Task task = getTask(unker, data);
        if (task == null) {
            ui.printSection("Unker don't think that is a valid number leh.");
        } else if (task.isDone()) {
            ui.printSection("You finish this task already leh:", "\t" + task, "");
        } else {
            task.setDone(true);
            ui.printSection("Good job, this task finish already:", "\t" + task, "");
        }
    }
}
