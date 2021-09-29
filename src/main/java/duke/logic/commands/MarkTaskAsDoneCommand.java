package duke.logic.commands;

import duke.logic.exceptions.TaskAlreadyDoneException;
import duke.logic.exceptions.TaskListEmptyException;
import duke.logic.exceptions.TaskNumOutOfBoundsException;
import duke.ui.Ui;

/**
 * Represents the command that when executed, marks the task at the given task number as done.
 */
public class MarkTaskAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + " X" + Ui.QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Mark task number X as done";
    public static final String MESSAGE_INVALID_FORMAT = "Invalid format! Please input a task number to be marked as done, "
            + Ui.LS + "in the format " + MESSAGE_COMMAND_FORMAT + ", where X is the task number!";
    private static final String MESSAGE_SUCCESS = "Well done! I've marked this task as done. ^_^";


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
            return new CommandResult(String.format(e.toString(), super.tasks.getNumTasks()));
        } catch (TaskAlreadyDoneException e) {
            return new CommandResult(e.toString());
        }
    }
}
