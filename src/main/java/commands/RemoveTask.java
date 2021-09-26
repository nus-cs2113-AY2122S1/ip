package commands;

import ui.Ui;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;

public class RemoveTask extends Command {
    protected Ui ui = new Ui();
    private final String input;

    /**
     * Changes the input attribute to the one given by the user.
     *
     * @param input The input by the user.
     */
    public RemoveTask(String input) {
        this.input = input;
    }

    /**
     * Removes a task from the task list and updates the database file accordingly.
     * If there is no input after the command word, the user will be prompted to include it.
     * If the input after the command word is not an integer number, the user will be prompted
     * with the correct input format.
     * If the task does not exist, the user will be shown an error message that informs them of that.
     *
     * @param tasks task list to be updated when a task is removed.
     */
    public void execute(TaskList tasks) {
        try {
            int index = Integer.parseInt(input.substring(7));
            if (index > tasks.getListSize()) {
                ui.showNoSuchTaskMessage();
            } else {
                Task thisTask = tasks.getTaskFromList(index - 1);
                ui.showDeleteTaskMessage(thisTask, (tasks.getListSize() - 1));
                tasks.removeTaskFromList(thisTask);
                Storage.updateDatabase();
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showNothingToRemoveMessage();
        } catch (NumberFormatException e) {
            ui.showRemoveCommandErrorMessage();
        }
    }
}
