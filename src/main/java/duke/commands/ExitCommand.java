package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.TaskList;

public class ExitCommand extends Command {
    public final static String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
    }
}
