package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Mark specified task as done.
 */
public class MarkDoneCommand extends Command {
    String arguments;

    public MarkDoneCommand(String command, String arguments) {
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
                tasks.getTaskAtIndex(taskIndex).markAsDone();
                ui.displayDoneMessage(tasks, taskIndex);
            }
        } catch (NumberFormatException e) {
            ui.displaySpecifyIntegerResponse();
        }
    }
}
