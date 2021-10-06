package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.task.ToDo;

import java.io.IOException;

/**
 * A representation of the command for adding a ToDo.
 */
public class AddToDoCommand extends Command {

    String commandArgs;

    /**
     * Class constructor specifying the command arguments.
     *
     * @param commandArgs The command arguments.
     */
    public AddToDoCommand (String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Executes the add Todo command.
     *
     * @param tasks The ArrayList of tasks.
     * @param ui The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     * @throws IOException If there is an error while saving data to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String description = commandArgs.trim();
        tasks.addTask(new ToDo(description));
        ui.printConfirmAdd(tasks.getTaskByIndex(tasks.getNumberOfTasks() - 1), tasks.getNumberOfTasks());
        storage.saveDataToFile(tasks);
    }

}
