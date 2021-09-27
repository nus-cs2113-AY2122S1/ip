package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Deletes Task based on given user input of task number.
 * Removal of Task from ArrayList tasks.
 * Out of bounds error handling is implemented.
 */
public class DeleteCommand extends Command {
    String arguments;

    public DeleteCommand(String command, String arguments) {
        super(command);
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = Integer.parseInt(arguments);
            if (taskNumber <= 0 || taskNumber > tasks.sizeOfTaskList()) {
                ui.displayTaskDoesNotExistMessage();
            } else {
                int taskIndex = taskNumber - 1;
                Task taskToDelete = tasks.getTaskAtIndex(taskIndex);
                tasks.removeTask(taskIndex);
                Storage.saveData(tasks);
                int taskListSize = tasks.sizeOfTaskList();
                ui.displayDeleteMessage(taskToDelete, taskListSize);
            }
        } catch (NumberFormatException e) {
            ui.displaySpecifyIntegerResponse();
        }

    }
}