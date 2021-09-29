package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * lists all tasks in the list to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all recorded tasks as a list with index numbers.\n"
            + "⮞ Example: " + COMMAND_WORD;

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            System.out.println("No task has added yet!");
        } else {
            System.out.println("Here are the tasks in your list:");
            tasks.printList();
        }
    }
}
