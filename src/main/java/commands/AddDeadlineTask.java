package commands;

import tasklist.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Deadline;

public class AddDeadlineTask extends Command {
    protected Ui ui = new Ui();
    private final String input;

    /**
     * Changes the input attribute to the one given by the user.
     *
     * @param input The input by the user.
     */
    public AddDeadlineTask(String input) {
        this.input = input;
    }

    /**
     * Adds a deadline task into the task list and updates the database accordingly.
     * If there is no input after the command word, the user will be prompted to include one in the correct format.
     * If there is no /by in the input, the user will be prompted to include it in the correct place.
     * If there is no description or deadline given, the user will be prompted to include it.
     *
     * @param tasks task list to be updated when a deadline task is added.
     */
    @Override
    public void execute(TaskList tasks) {
        if (input.trim().length() <= 8) {
            ui.showMissingTaskOrDeadlineMessage();
        } else if (!input.toLowerCase().contains("/by")) {
            ui.showMissingByMessage();
        } else {
            int slashIndex = input.toLowerCase().indexOf("/by");
            String task = input.substring(9, slashIndex).trim();
            String dueDate = input.substring(slashIndex + 3).trim();
            if (task.length() <= 0) {
                ui.showMissingTaskDescriptionMessage();
            } else if (dueDate.length() <= 0) {
                ui.showMissingTaskDeadlineMessage();
            } else {
                tasks.addTaskToList(new Deadline(task, dueDate));
                int taskIndex = tasks.getListSize() - 1;
                ui.showTaskAddedMessage(tasks.getTaskFromList(taskIndex), tasks.getListSize());
                int indexOfAddedTask = tasks.getListSize() - 1;
                Deadline addedDeadline = (Deadline) tasks.getTaskFromList(indexOfAddedTask);
                String deadlineInput = addedDeadline.getDescription() + "/by" + addedDeadline.getDeadline();
                Storage.appendDatabase("D", deadlineInput, "0");
            }
        }
    }
}
