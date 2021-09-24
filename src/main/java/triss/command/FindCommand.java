package triss.command;

import triss.exception.TrissException;
import triss.parser.Parser;
import triss.storage.Storage;
import triss.tasklist.TaskList;
import triss.ui.Ui;

public class FindCommand extends Command{

    @Override
    public void execute(Ui ui, Parser parser, TaskList tasklist, Storage storage) throws TrissException {
        tasklist.findTaskByKeyword(ui.getUserInput());
    }
}
