package duke.commands;

import duke.task.Task;
import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";


    public DeadlineCommand() {
    }

    @Override
    public void execute(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(input, ui, "D");
        storage.saveToFile(tasks.getTasks());
    }
}
