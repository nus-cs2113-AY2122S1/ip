package duke.commands;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

public class DoneCommand extends Command{

    private TaskList tlist;
    private Task t;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for command when the user marks a task as done.
     * @param tlist List where task is found
     * @param index Index of task to be marked done
     * @param ui Handles interaction with user.
     * @param storage updates "data.txt" file
     */
    public DoneCommand(TaskList tlist, int index, Ui ui,Storage storage){
        this.tlist = tlist;
        tlist.get(index).taskDone();
        this.t = tlist.get(index);
        this.ui = ui;
        this.storage = storage;
    }

    public void run() throws IOException {
        ui.done(t, tlist);
        t.taskDone();
        storage.save(tlist);
    }
}
