package triss.command;

import triss.parser.Parser;
import triss.storage.Storage;
import triss.tasklist.TaskList;
import triss.ui.Ui;
import triss.exception.TrissException;

public class AddDeadlineCommand extends Command{

    @Override
    public void execute(Ui ui, Parser parser, TaskList tasklist, Storage storage) throws TrissException {
        tasklist.createNewDeadline(ui.getUserInput(), false);
    }
}
