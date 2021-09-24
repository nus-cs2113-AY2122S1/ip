package duke.commands;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.DukeException;
import duke.tasks.TaskList;

import java.io.IOException;

public class AddTaskCommand extends Command{

    private  Task t;
    private TaskList tlist;
    private Ui ui;
    private Storage storage;

    public AddTaskCommand(TaskList tlist, Task t, Ui ui, Storage storage){
        this.tlist = tlist;
        this.t = t;
        this.ui = ui;
        this.storage = storage;
    }

    public  void run() throws IOException {
        tlist.add(t);
        ui.add(t,tlist);
        storage.save(tlist);

    }

}
