package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * A representation of the command for marking a task as done.
 */
public class MarkDoneCommand extends Command {

    String commandArgs;

    /**
     * Class constructor specifying the command arguments.
     *
     * @param commandArgs The command arguments.
     */
    public MarkDoneCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Execute the mark done command.
     *
     * @param tasks The ArrayList of tasks.
     * @param ui The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     * @throws IOException If there is an error while saving data to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        int taskIndex = Integer.parseInt(commandArgs.strip()) - 1;
        Task currentTask = tasks.getTaskByIndex(taskIndex);
        currentTask.markAsDone();
        ui.printConfirmMarkDone(currentTask);
        storage.saveDataToFile(tasks);
    }
}
