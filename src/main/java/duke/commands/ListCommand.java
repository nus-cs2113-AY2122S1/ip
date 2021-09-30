package duke.commands;

import duke.Ui;
import duke.tasks.TaskList;

public class ListCommand extends Command{
    private TaskList tlist;
    private Ui ui;

    /**
     * Constructor for command when the user wishes to look at the list of tasks.
     *
     * @param tlist list of Tasks to be viewed
     * @param ui Handles interaction with the user
     */
    public ListCommand(TaskList tlist, Ui ui){
        this.tlist = tlist;
        this.ui = ui;
    }

    public void run(){
        ui.printList(tlist);
    }

}
