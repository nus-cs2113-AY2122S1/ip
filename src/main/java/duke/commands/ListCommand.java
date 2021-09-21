package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * This class is displays all task in the task list
 */
public class ListCommand extends Command {
    /**
     * Sets up the list command to be execute by the application
     *
     * @param command it is the name of the command
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Executes the command and display all the task in the task list. Save the data in the task list into the file
     *
     * @param tasks the task list that stores task for the application which is manipulated based on the command
     * @param ui the user interface that interacts with users which prints messages based on the different commands
     * @param storage the area which data can be read from and written to, to save task list data permanently
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showTaskList(tasks);
    }
}
