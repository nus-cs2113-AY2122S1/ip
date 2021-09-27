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

    /**
     * Constructor for command when user wants to delete a task fron the list.
     * @param tlist list of tasks where task to be deleted is found
     * @param index index of task to be deleted
     * @param ui Handles interaction with user.
     * @param storage updates "data.txt" file
     */
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
