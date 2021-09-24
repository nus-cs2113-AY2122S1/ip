package triss.command;

import triss.parser.Parser;
import triss.storage.Storage;
import triss.tasklist.TaskList;
import triss.ui.Ui;
import triss.exception.TrissException;

public class AddEventCommand extends Command{

    @Override
    public void execute(Ui ui, Parser parser, TaskList tasklist, Storage storage) throws TrissException {
        tasklist.createNewEvent(ui.getUserInput(), false);
    }
}
