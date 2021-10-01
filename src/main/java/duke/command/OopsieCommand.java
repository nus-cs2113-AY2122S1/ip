package duke.command;

import duke.data.TaskList;
import duke.ui.Ui;

/**
 * Default command to inform the user of an incorrect command syntax
 * A <code>Oopsie</code> command can be called with any prefix that's NOT in <code>CommandPrefix</code> enum.
 */
public class OopsieCommand extends Command {
    public OopsieCommand() {
        super(CommandPrefix.OOPSIE);
    }

    @Override
    public void execute(TaskList tasks) {
        Ui.inputFailMessage();
    }
}
