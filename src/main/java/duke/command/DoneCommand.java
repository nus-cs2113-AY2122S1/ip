package duke.command;

import duke.exception.InvalidIndexException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.program.LizUi;
import duke.program.TaskList;


public class DoneCommand extends Command {

    private String[] lineArgs;

    public DoneCommand(String[] lineArgs) {
        this.lineArgs = lineArgs;
    }

    /**
     * Executes command that marks a task as done on the task list.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    @Override
    public void executeCommand(TaskList tasks, LizUi ui) {
        markAsDoneWithExceptionHandling(lineArgs, tasks, ui);
    }

    /**
     * Marks a specified task as done with corresponding exception handling. Checked exceptions when caught, print
     * a corresponding error message.
     * @param lineArgs parsed array of line arguments. Contains done command and done index number.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    private void markAsDoneWithExceptionHandling(String[] lineArgs, TaskList tasks, LizUi ui) {
        try {
            tasks.markAsDone(tasks.getTaskList(), lineArgs, ui);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printEmptyIndexAfterDoneMessage();
        } catch (InvalidIndexException | NumberFormatException e) {
            ui.printInvalidDoneOrDeleteMessage();
        } catch (TaskIndexOutOfBoundsException e) {
            ui.printIndexOutOfBoundsMessage();
        }
    }
}
