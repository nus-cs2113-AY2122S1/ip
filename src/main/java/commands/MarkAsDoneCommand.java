package commands;

import common.Messages;
import task.Task;

import static ui.Ui.INDENT;


public class MarkAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the task corresponding to the task number as complete. \n"
            + "Example: done {TASK_NUMBER}";
    public static final String MESSAGE_DONE_SUCCESS = "Wow. You finally completed a task after lazying around all day.\n"
            + INDENT + "Completed: %1$s";


    public MarkAsDoneCommand(int targetDisplayIndex) {
        super(targetDisplayIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            final int taskIndex = getTargetDisplayIndex();
            final Task toComplete = getTargetTask();
            taskManager.markTaskAsDone(taskIndex);
            return new CommandResult(String.format(MESSAGE_DONE_SUCCESS, toComplete.toString()));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_FOUND);
        }
    }
}
