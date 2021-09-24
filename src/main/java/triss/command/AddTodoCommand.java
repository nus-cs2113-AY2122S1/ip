package triss.command;

import triss.parser.Parser;
import triss.storage.Storage;
import triss.tasklist.TaskList;
import triss.ui.Ui;
import triss.exception.TrissException;

public class AddTodoCommand extends Command{

    @Override
    public void execute(Ui ui, Parser parser, TaskList tasklist, Storage storage) throws TrissException {
        tasklist.createNewTodo(ui.getUserInput(), false);
    }
}
