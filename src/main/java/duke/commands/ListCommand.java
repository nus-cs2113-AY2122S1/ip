package duke.commands;

import duke.Ui;
import duke.tasks.TaskList;

public class ListCommand extends Command{
    private TaskList tlist;
    private Ui ui;
    public ListCommand(TaskList tlist, Ui ui){
        this.tlist = tlist;
        this.ui = ui;
    }

    public void run(){
        ui.printList(tlist);
    }

}
