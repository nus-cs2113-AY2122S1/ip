package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ListCommand extends Command{
    
    @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        list.printList();
     }

    @Override
    public boolean isExit() {
        return false;
    }
}
