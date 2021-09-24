package triss.command;

import triss.Parser;
import triss.Storage;
import triss.TaskList;
import triss.Ui;
import triss.exception.TrissException;

public class DeleteCommand extends Command{

    @Override
    public void execute(Ui ui, Parser parser, TaskList tasklist, Storage storage) throws TrissException {
        tasklist.deleteTask(ui.getUserInput());
    }
}
