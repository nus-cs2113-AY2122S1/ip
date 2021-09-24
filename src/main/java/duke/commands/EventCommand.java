package duke.commands;

import duke.task.Task;
import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public EventCommand() {
    }

    @Override
    public void execute(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(input, ui, "E");
        storage.saveToFile(tasks.getTasks());
    };
}
