package duke.command;

import duke.program.LizUi;
import duke.program.TaskList;
import duke.task.Task;

import java.util.ArrayList;

public class InvalidCommand extends Command {
    @Override
    public void executeCommand(TaskList tasks, LizUi ui) {
        ui.printInvalidCommandMessage();
    }


}
