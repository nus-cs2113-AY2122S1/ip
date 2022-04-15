package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * This class is used to terminate the application
 */
public class ExitCommand extends Command {
    /**
     * Sets up the exit command to be execute by the application
     *
     * @param command it is the name of the command
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Executes the command and terminate the application. Save the data in the task
     * list into the file
     *
     * @param tasks the task list that stores task for the application which is manipulated based on the command
     * @param ui the user interface that interacts with users which prints messages based on the different commands
     * @param storage the area which data can be read from and written to, to save task list data permanently
     * @throws IOException is thrown if there is an error in writing data from task list into file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showGoodByeMessage();
        storage.store(tasks);
    }
}
