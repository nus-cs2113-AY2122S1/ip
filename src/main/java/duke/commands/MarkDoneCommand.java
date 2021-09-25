package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;


public class MarkDoneCommand extends Command {
    String arguments;

    /**
     * Mark specified task as done.
     *
     * @param arguments taskNumber
     */
    public MarkDoneCommand(String command, String arguments) {
        super(command);
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (arguments.equals("")) {
            String output = " ☹ OOPS!!! You did not specify a task number.\n";
            ui.printOutput(output);
        } else { // Need to handle non-integer inputs
            int taskNumber = Integer.parseInt(arguments);
            if (taskNumber <= 0 || taskNumber > tasks.sizeOfTaskList()) {
                ui.displayTaskDoesNotExistMessage();
            } else {
                int taskIndex = taskNumber - 1;
                tasks.getTaskAtIndex(taskIndex).markAsDone();
                ui.displayDoneMessage(tasks, taskIndex);
            }
        }

    }
}
