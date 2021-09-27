package duke.commands;

import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class FindCommand extends Command{
    private TaskList tlist;
    private Ui ui;
    private String description;

    public FindCommand(TaskList tlist, Ui ui,String description){
        this.tlist = tlist;
        this.ui = ui;
        this.description = description;
    }

    public void run(){
        TaskList possibleTasks = tlist.find(this.description);
        ui.find(possibleTasks, description);
    }
}
