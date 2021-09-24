package commands;

import tasklist.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Deadline;

public class AddDeadline extends Command {
    protected Ui ui = new Ui();
    private final String input;

    public AddDeadline(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks) {
        if (input.trim().length() <= 8) {
            ui.showMissingTaskOrDeadlineMessage();
        } else {
            int slashIndex = input.toLowerCase().indexOf("/by");
            String task = input.substring(9, slashIndex).trim();
            String dueDate = input.substring(slashIndex + 3).trim();
            if (task.length() <= 0) {
                ui.showMissingTaskDescriptionMessage();
            } else if (!input.toLowerCase().contains("/by")) {
                ui.showMissingByMessage();
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
