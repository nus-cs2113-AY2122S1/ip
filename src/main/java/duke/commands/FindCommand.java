package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Find all tasks that contains a specific keyword in description of task.
 * Display them to the user in sorted order.
 */
public class FindCommand extends Command {
    String arguments;

    public FindCommand(String command, String arguments) {
        super(command);
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (arguments.equals("")) {
            ui.displayEmptyKeywordResponse();
        } else {
            String output = " Here are the matching tasks in your list:\n";
            int numbering = 1;
            tasks.sortByDateTime();
            for (int i = 1; i < tasks.sizeOfTaskList() + 1; i++) {
                Task task = tasks.getTaskAtIndex(i - 1);
                if (task.getDescription().contains(arguments)) {
                    output = output + " " + numbering + "." + task + "\n";
                    numbering++;
                }
            }
            ui.printOutput(output);
        }
    }
}
