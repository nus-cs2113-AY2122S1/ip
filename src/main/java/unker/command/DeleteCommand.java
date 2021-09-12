package unker.command;

import unker.Unker;
import unker.task.Task;
import unker.ui.UI;

public class DeleteCommand extends Command {

    protected DeleteCommand() {
        super("delete", "delete <index>");
    }
    
    private Task removeTask(Unker unker, String data) {
        try {
            int index = Integer.parseInt(data) - 1;
            return unker.removeTask(index);
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            // Return nothing if the task is invalid.
        }
        return null;
    }
    
    @Override
    public void execute(UI ui, Unker unker, String data) throws InvalidCommandException {
        Task removedTask = removeTask(unker, data);
        if (removedTask == null) {
            throw new InvalidCommandException("Unker don't think that is a valid number leh.", this);
        } else {
            ui.printSection("Okay Unker help you remove this task for you already:", "\t" + removedTask, "");
        }
    }
}
