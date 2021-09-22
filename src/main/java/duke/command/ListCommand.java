package duke.command;

import duke.program.LizUi;
import duke.program.TaskList;
import duke.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void executeCommand(TaskList tasks, LizUi ui) {
        ui.printTaskList(tasks.getTaskCount(), tasks.getTaskList());
    }

}
