package duke.commands;

import duke.task.Deadline;
import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public DeleteCommand() {
    }

    public void execute(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.deleteTask(input, ui);
        storage.saveToFile(tasks.getTasks());
    }
}
