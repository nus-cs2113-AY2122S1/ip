package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the command to exit the program
 */
public class ExitCommand extends Command{

    /**
     * Executes the command to print the goodbye message
     *
     * @param list The tasklist instance to handle interactions with the ArrayList of task
     * @param ui The ui instance to handle interactions with the user
     * @param storage The storage instance to handle interactions with the text file
     */
     @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
         ui.printGoodbyeMessage();
     }

    /**
     * @return returns true if the command to exit the application is given
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
