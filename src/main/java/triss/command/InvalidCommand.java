package triss.command;

import triss.parser.Parser;
import triss.storage.Storage;
import triss.tasklist.TaskList;
import triss.ui.Ui;
import triss.exception.TrissException;

public class InvalidCommand extends Command{

    @Override
    public void execute(Ui ui, Parser parser, TaskList tasklist, Storage storage) throws TrissException {
        String errorMessage = "Oof, I didn't understand your command! Let's try that again.\n"
                + " \n" + "Type a todo in this format:\n" + "    todo Eat with Friends";
        throw new TrissException(errorMessage);
    }
}
