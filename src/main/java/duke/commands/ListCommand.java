package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Display all Tasks in TaskList in sorted order.
 */
public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sortByDateTime();
        String output = " Here are the tasks in your list:\n";
        for (int i = 1; i < tasks.sizeOfTaskList() + 1; i++) {
            output = output + " " + i + "." + tasks.getTaskAtIndex(i - 1).toString() + "\n";
        }
        ui.printOutput(output);
    }
}
