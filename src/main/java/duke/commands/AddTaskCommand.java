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

    /**
     * Constructor for command when user wants to add a new task. Task can be either a normal task, an Event or a Deadline
     * @param tlist list for task to be added to
     * @param t Task to be added. Either be a normal task, an Event or a Deadline
     * @param ui Handles interaction with the user
     * @param storage updates "data.txt" file
     */
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
