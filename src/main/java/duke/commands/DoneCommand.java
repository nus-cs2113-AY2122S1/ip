package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command Class for marking tasks as completed.
 */
public class DoneCommand extends Command {
    public static final int END_OF_DONE_INDEX = 4;

    /**
     * Initializes new DoneCommand object.
     * @param fullCommand full user input as a string
     */
    public DoneCommand(String fullCommand) {
        this.isExit = false;
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the marking of specified task as done.
     * If task is not found or invalid command is given, shows error message to user.
     * @param tasks TaskList object of all tasks in the programme
     * @param ui Ui object for calling Ui methods
     * @param storage Storage object for writing to memory
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            handleDone(tasks, ui, storage);
        } catch (NumberFormatException e) {
            ui.showMissingDoneIndexError();
        } catch (IndexOutOfBoundsException e) {
            ui.showInvalidTaskIndexError();
        }
    }

    /**
     * Marks the specified task as done if currently uncompleted, and outputs confirmation to user.
     * Else outputs notification to user that task is already completed.
     * @param tasks TaskList object containing all tasks as an ArrayList
     * @param ui Ui object for calling of ui methods
     * @param storage Storage object for writing to memory
     */
    private void handleDone(TaskList tasks, Ui ui, Storage storage) {
        int taskNumber = Integer.parseInt(fullCommand.substring(END_OF_DONE_INDEX).trim());
        int taskIndex = taskNumber - 1;
        if (tasks.getTask(taskIndex).isDone()) {
            ui.showAlreadyDone();
        } else {
            tasks.getTask(taskIndex).markAsDone();
            ui.showDone(taskIndex, tasks);
            storage.writeDone(taskIndex);
        }
    }
}
