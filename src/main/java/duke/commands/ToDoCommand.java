package duke.commands;

import duke.Duke;
import duke.task.Task;
import duke.utilities.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class ToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    protected Task taskItem;

    public ToDoCommand() {
    }

    @Override
    public void execute(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(input, ui, "T");
        storage.saveToFile(tasks.getTasks());
    };
}
