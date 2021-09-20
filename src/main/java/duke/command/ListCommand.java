package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    private static final String MESSAGE_LIST_EMPTY = "Task list is empty.";

    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printMessage(MESSAGE_LIST_EMPTY);
        } else {
            ui.printMessage(tasks.toString());
        }
    }
}
