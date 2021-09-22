package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.startup.Ui;

public class OopsieCommand extends Command{
    public OopsieCommand() {
        super(CommandPrefix.oopsie);
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
    }
}
