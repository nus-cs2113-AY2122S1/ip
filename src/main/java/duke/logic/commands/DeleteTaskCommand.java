package duke.logic.commands;

import duke.logic.commands.exceptions.TaskListEmptyException;
import duke.logic.commands.exceptions.TaskNumOutOfBoundsException;

import static duke.ui.Ui.LS;
import static duke.ui.Ui.QUOTATION;

public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + " X" + QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Delete task number X";
    public static final String MESSAGE_INVALID_FORMAT = "Invalid format! Please input a task number to be deleted, "
        + LS + "in the format " + MESSAGE_COMMAND_FORMAT + ", where X is the task number!";
    private static final String MESSSAGE_SUCCESS = "Alrightys! I have removed the following task: %1$s "
            + LS + "Current number of tasks: %2$d";
    private static final String MESSAGE_TASK_NUM_INVALID = "Please input a valid task number from 1 to %d!";

    private int taskNum;

    public DeleteTaskCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public CommandResult execute() {
        try {
            final String deletedTaskString = super.tasks.getTaskAtNum(this.taskNum).toString();
            super.tasks.deleteTask(this.taskNum);
            return new CommandResult(String.format(MESSSAGE_SUCCESS, deletedTaskString, super.tasks.getNumTasks()));
        } catch (TaskListEmptyException e) {
            return new CommandResult(e.toString());
        } catch (TaskNumOutOfBoundsException e) {
            return new CommandResult(String.format(MESSAGE_TASK_NUM_INVALID, super.tasks.getNumTasks()));
        }
    }
}
