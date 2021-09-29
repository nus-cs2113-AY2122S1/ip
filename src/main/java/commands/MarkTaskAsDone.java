package commands;

import tasklist.TaskList;
import ui.Ui;
import storage.Storage;


public class MarkTaskAsDone extends Command {
    private final String input;

    /**
     * Changes the input attribute to the one given by the user.
     *
     * @param input The input by the user.
     */
    public MarkTaskAsDone(String input) {
        this.input = input;
    }

    /**
     * Marks a task as done and updates that task in both the task list and database file.
     * If there is no input after the command word, the user will be prompted to include it.
     * If the input after the command word is not an integer number, the user will be prompted
     * with the correct input format.
     * If the task does not exist, the user will be shown an error message that informs them of that.
     * If the task is already marked as done, the user will be shown an error message that informs them of that.
     *
     *
     * @param tasks task list to be updated when a task is marked as done.
     * @param ui Access to messages.
     * @param storage storage access.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(input.substring(5).trim());
            int taskCount = tasks.getListSize();
            if ((index > taskCount) || (index <= 0)) {
                ui.showNoSuchTaskMessage();
            } else if (tasks.getTaskFromList(index - 1).getStatusIcon().equals("X")) {
                ui.showTaskAlreadyDoneMessage();
            } else {
                tasks.getTaskFromList(index - 1).markAsDone();
                ui.showTaskCompleteMessage(tasks.getTaskFromList(index - 1));
                Storage.updateDatabase();
            }
        } catch (StringIndexOutOfBoundsException e) {
            ui.showNoTaskSpecifiedMessage();
        } catch (NumberFormatException e) {
            ui.showDoneCommandErrorMessage();
        }
    }
}
