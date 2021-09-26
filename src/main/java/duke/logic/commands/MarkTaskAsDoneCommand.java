package duke.logic.commands;

import duke.logic.commands.exceptions.TaskListEmptyException;
import duke.logic.commands.exceptions.TaskNumOutOfBoundsException;

import static duke.ui.Ui.LS;
import static duke.ui.Ui.QUOTATION;

public class MarkTaskAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + " X" + QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Mark task number X as done";
    public static final String MESSAGE_INVALID_FORMAT = "Invalid format! Please input a task number to be marked as done, "
            + LS + "in the format " + MESSAGE_COMMAND_FORMAT + ", where X is the task number!";
    private static final String MESSAGE_SUCCESS = "Well done! I've marked this task as done. ^_^";
    private static final String MESSAGE_TASK_NUM_INVALID = "Please input a valid task number from 1 to %d !";

    private int taskNum;

    public MarkTaskAsDoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public CommandResult execute() {
        try {
            super.tasks.markTaskAsDone(taskNum);
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (TaskListEmptyException e) {
            return new CommandResult(e.toString());
        } catch (TaskNumOutOfBoundsException e) {
            return new CommandResult(String.format(MESSAGE_TASK_NUM_INVALID, super.tasks.getNumTasks()));
        }
    }
}
