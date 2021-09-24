package triss.command;

import triss.Parser;
import triss.Storage;
import triss.TaskList;
import triss.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(Ui ui, Parser parser, TaskList tasklist, Storage storage) {
        tasklist.printAllTasks();
    }
}
