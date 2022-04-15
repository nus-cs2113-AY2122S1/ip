package duke.command;

import duke.common.Messages;
import duke.task.TaskManagerException;

/**
 * Done command to set a specified task to done by its task number from list command.
 */
public class DoneCommand extends Command {

    final public static String COMMAND_WORD = "done";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + TAG_TASK_NUMBER;

    public DoneCommand(int targetIndex) {
        super(targetIndex);
        this.hasDataChange = true;
    }

    /**
     * Method to execute the command by calling the TaskManager to perform its specified operation.
     */
    @Override
    public CommandResult execute() throws TaskManagerException {
        taskManager.setTaskToDone(this.getTaskIndex());
        String result = Messages.MESSAGE_COMMAND_DONE + "\n";
        result += taskManager.getTaskInfo(this.getTaskIndex() - 1);
        return new CommandResult(result);
    }
}
