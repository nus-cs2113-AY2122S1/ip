package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the command to list the tasks in the TaskList
 */
public class ListCommand extends Command{

    /**
     * Lists out the tasks in the TaskList
     *
     * @param list The tasklist instance to handle interactions with the ArrayList of task
     * @param ui The ui instance to handle interactions with the user
     * @param storage The storage instance to handle interactions with the text file
     */
    @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        list.printList();
     }

    /**
     * @return returns true if the command to exit the application is given
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
