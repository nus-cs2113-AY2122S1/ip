package duke.commands;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {

    int index;

    /**
     * Command when user wants a find certain Tasks (e.g. Todo, Deadline, Event), based on a matching keyword(s).
     * When executed, it will find the matching task(s) in the TaskList,
     *
     * @param index Index of the task in the TaskList, to be marked done.
     */

    public DoneCommand(int index){
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui) {
        taskList.get(index).markDone();
        ui.doneTask(index, taskList);
    }

    public boolean isExit(){
        return false;
    }
}
