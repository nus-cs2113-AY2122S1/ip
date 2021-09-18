package duke;

import duke.exceptions.DukeInvalidTaskIndex;
import duke.exceptions.DukeTaskAlreadyCompletedException;
import duke.tasks.TaskList;
import java.io.IOException;

public class MarkAsDoneCommand extends Command{
    public static final String COMMAND_WORD = "done";
    //Note: not 0 based index
    private int indexOfTaskDone;
    
    public MarkAsDoneCommand(int indexOfTaskDone) {
        this.indexOfTaskDone = indexOfTaskDone;
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeInvalidTaskIndex,
            DukeTaskAlreadyCompletedException,
            IOException {
        tasks.setTaskAsDone(indexOfTaskDone);
        storage.refreshData(tasks.getTasks());
        ui.acknowledgeDoneCommand(tasks.getTasks().get(indexOfTaskDone - 1));
    }
}
