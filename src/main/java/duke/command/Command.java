package duke.command;

import duke.program.LizUi;
import duke.program.TaskList;
import duke.task.Task;

import java.util.ArrayList;

public abstract class Command {

    public abstract void executeCommand(TaskList tasks, LizUi ui);

    public boolean isExit() {
        return false;
    }
}
