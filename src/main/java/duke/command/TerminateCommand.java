package duke.command;

import duke.task.TaskManager;

public class TerminateCommand extends Command {

    public TerminateCommand(TaskManager taskManager) {
        super(taskManager, "");
    }

    @Override
    public CommandResult executeCommand() {
        return new CommandResult(super.taskManager, false, true);
    }

}
