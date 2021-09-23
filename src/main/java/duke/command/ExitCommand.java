package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command{

     @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        
     }

    @Override
    public boolean isExit() {
        return true;
    }
}
