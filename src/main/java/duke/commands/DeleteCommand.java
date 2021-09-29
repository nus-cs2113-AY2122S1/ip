package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

import java.io.FileNotFoundException;

/**
 * Command Class for deleting tasks both from TaskList and memory.
 */
public class DeleteCommand extends Command {
    public static final int END_OF_DELETE_INDEX = 6;

    /**
     * Initializes new DeleteCommand object.
     * @param fullCommand full user input as a string
     */
    public DeleteCommand(String fullCommand) {
        this.isExit = false;
        this.fullCommand = fullCommand;
    }

    /**
     * Deletes the specified task and shows confirmation to user.
     * If task is not found or invalid command is given, shows error message to user.
     * @param tasks TaskList object of all tasks in the programme
     * @param ui Ui object for calling Ui methods
     * @param storage Storage object for writing to memory
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deletedTask = handleDeleteTask(tasks, ui, storage);
            ui.showDeleteConfirmation(deletedTask, tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidDeleteIndexError();
        } catch (NumberFormatException e) {
            ui.showMissingDeleteIndexError();
        }
    }

    /**
     * Deletes and returns task of specified index from {@code tasks} and memory if task is found.
     * Else shows a delete error message.
     * @param tasks TaskList object containing all tasks as an ArrayList
     * @param ui Ui object for calling of ui methods
     * @param storage Storage object for writing to memory
     * @return deleted task
     */
    private Task handleDeleteTask(TaskList tasks, Ui ui, Storage storage) {
        int taskNumber = Integer.parseInt(fullCommand.substring(END_OF_DELETE_INDEX).trim());
        int taskIndex = taskNumber - 1;
        Task deletedTask = tasks.getTask(taskIndex);
        tasks.getTasks().remove(taskIndex);
        try {
            storage.deleteTaskFromMem(taskIndex);
        } catch (FileNotFoundException e) {
            ui.showDeleteError();
        }
        return deletedTask;
    }

}
