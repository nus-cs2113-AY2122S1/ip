package duke.logic.commands;

import duke.logic.exceptions.TaskListEmptyException;
import duke.logic.exceptions.TaskNumOutOfBoundsException;
import duke.ui.Ui;

/**
 *  Represents the command that when executed, deletes the task at the given task number.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + " X" + Ui.QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Delete task number X";
    public static final String MESSAGE_INVALID_FORMAT = "Invalid format! Please input a task number to be deleted, "
        + Ui.LS + "in the format " + MESSAGE_COMMAND_FORMAT + ", where X is the task number!";
    private static final String MESSAGE_SUCCESS = "Alrightys! I have removed the following task: %1$s "
            + Ui.LS + "Current number of tasks: %2$d";

    private int taskNum;

    public DeleteTaskCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public CommandResult execute() {
        try {
            final String deletedTaskString = super.tasks.getTaskAtNum(this.taskNum).toString();
            super.tasks.deleteTask(this.taskNum);
            return new CommandResult(String.format(MESSAGE_SUCCESS, deletedTaskString, super.tasks.getNumTasks()));
        } catch (TaskListEmptyException e) {
            return new CommandResult(e.toString());
        } catch (TaskNumOutOfBoundsException e) {
            return new CommandResult(String.format(e.toString(), super.tasks.getNumTasks()));
        }
    }
}
