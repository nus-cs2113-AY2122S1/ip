package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class is used to find all task which contains a specific substring in its description field
 */
public class FindCommand extends Command {
    private String filterWord;

    /**
     * Sets up the find command to be execute by the application
     *
     * @param Command it is the name of the command
     * @param filterWord it is the substring we want to filter each tasks' description by
     */
    public FindCommand(String Command, String filterWord) {
        super(Command);
        this.filterWord = filterWord;
    }

    /**
     * Executes the command and find all task which contains a specific substring in its description field and display it.
     * Save the data in the task list into the file
     *
     * @param tasks the task list that stores task for the application which is manipulated based on the command
     * @param ui the user interface that interacts with users which prints messages based on the different commands
     * @param storage the area which data can be read from and written to, to save task list data permanently
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        TaskList filteredTasks = tasks.getFilteredTask(filterWord);
        ui.showFilteredTask(filteredTasks, filterWord);
    }
}
