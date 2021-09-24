package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command{
    private TaskList tlist;
    private Task t;
    private Ui ui;
    private Storage storage;

    public DeleteCommand(TaskList tlist, int index, Ui ui, Storage storage) {
        this.tlist = tlist;
        this.t = tlist.get(index);
        this.ui = ui;
        this.storage = storage;
    }

    public void run() throws IOException {
        ui.delete(t,tlist);
        tlist.remove(t);
        storage.save(tlist);
    }
}
