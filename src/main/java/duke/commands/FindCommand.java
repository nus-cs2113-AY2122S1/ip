package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.MissingSearchTermException;
import duke.tasks.Task;

/**
 * Command Class that executes search for all tasks containing corresponding keyword.
 */
public class FindCommand extends Command {
    public static final int END_OF_FIND_INDEX = 4;

    /**
     * Initializes new FindCommand object.
     * @param fullCommand full user input as a string
     */
    public FindCommand(String fullCommand) {
        this.isExit = false;
        this.fullCommand = fullCommand;
    }

    /**
     * Searches through TaskList and prints all tasks that contains the search term in its description.
     * @param tasks TaskList object of all tasks in the programme
     * @param ui Ui object for calling Ui methods
     * @param storage Storage object for writing to memory
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            find(tasks, ui, storage);
        } catch(DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Parses user command, executes search based on search term and prints out all tasks found.
     * @param tasks TaskList object of all tasks in the programme
     * @param ui Ui object for calling Ui methods
     * @param storage Storage object for writing to memory
     * @throws MissingSearchTermException If search term is not inputted by user
     */
    public void find(TaskList tasks, Ui ui, Storage storage) throws MissingSearchTermException {
        String searchKey = fullCommand.substring(END_OF_FIND_INDEX).trim();
        if (searchKey.equals("")) {
            throw new MissingSearchTermException();
        }
        int taskNumber = 1;
        System.out.println("Here are the matching tasks in your list:");
        for(Task task : tasks.getTasks()) {
            if(task.getDescription().contains(searchKey)) {
                System.out.println((taskNumber) + "." + task);
            }
            taskNumber++;
        }
    }
}
