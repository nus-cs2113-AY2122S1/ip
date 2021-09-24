package duke.commands;

import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public DoneCommand() {
    }

    public void execute(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.setTaskAsDone(input, ui);
        storage.saveToFile(tasks.getTasks());
    }
}
