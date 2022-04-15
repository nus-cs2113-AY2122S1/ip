package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Command Class for displaying of all tasks.
 */
public class ListCommand extends Command {
    /**
     * Initializes new ListCommand object.
     */
    public ListCommand() {
        this.isExit = false;
        this.fullCommand = "";
    }

    /**
     * Outputs list of all tasks to the user.
     * Completed tasks are marked with an 'X'.
     * @param tasks TaskList object of all tasks in the programme
     * @param ui Ui object for calling Ui methods
     * @param storage Storage object for writing to memory
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        int taskNumber = 1;
        for (Task task : tasks.getTasks()) {
            System.out.println((taskNumber) + "." + task);
            taskNumber++;
        }
    }

}
