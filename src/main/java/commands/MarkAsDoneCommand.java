package commands;

import task.Task;

import static common.Messages.MESSAGE_TASK_NOT_FOUND;

public class MarkAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = "done: Marks the task corresponding to the task number as complete.\n"
            + "\tFormat: done {TASK_NUMBER}\n"
            + "\tExample: done 2";
    public static final String MESSAGE_SUCCESS = "\nCompleted: %1$s\n"+
            "\tWow. You finally completed a task after lazying around all day.\n";

    public MarkAsDoneCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute() {
        try {
            Task toMarkDone = getTargetTask();
            int targetIndex = getTargetIndex();
            taskManager.markTaskAsDone(targetIndex);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toMarkDone.toString()));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        }
    }
}
