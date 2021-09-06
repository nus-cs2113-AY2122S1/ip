package unker.command;

import unker.task.Task;
import unker.ui.UI;
import unker.Unker;

/**
 * Command that lists all {@link unker.task.Task} added to the task manager {@link unker.Unker} 
 *
 * Usage in UI: list 
 */
public class ListCommand extends Command {

    protected ListCommand() {
        super("list");
    }

    @Override
    public void execute(UI ui, Unker unker, String data) {
        if (unker.isTasksEmpty()) {
            ui.printSection("Wah seh, you got nothing in your to-do list leh.");
        } else {
            StringBuilder output = new StringBuilder("This is what you give me:\n");
            int toBeCompleted = 0;
            for (int i = 0; i < unker.getTotalTasks(); i++) {
                Task task = unker.getTask(i);
                if (!task.isDone()) {
                    toBeCompleted++;
                }
                output.append(String.format("%d. %s\n", i + 1, task));
            }
            output.append(String.format("\nYou still got %d task(s) left to do.", toBeCompleted));
            ui.printSection(output.toString());
        }

    }
}
