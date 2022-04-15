package duke.command;

import duke.exception.InvalidIndexException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.program.LizUi;
import duke.program.TaskList;

public class DeleteCommand extends Command {

    private String[] lineArgs;

    public DeleteCommand(String[] lineArgs) {
        this.lineArgs = lineArgs;
    }

    /**
     * Executes command that deletes a task off the task list.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    @Override
    public void executeCommand(TaskList tasks, LizUi ui) {
        deleteTaskWithExceptionHandling(lineArgs, tasks, ui);
    }

    /**
     * Deletes a specified task with corresponding exception handling. Checked exceptions when caught, print
     * a corresponding error message.
     * @param lineArgs parsed array of line arguments. Contains delete command and delete index number.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    private void deleteTaskWithExceptionHandling(String[] lineArgs, TaskList tasks, LizUi ui) {
        try {
            tasks.deleteTask(tasks.getTaskList(), lineArgs, ui);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printEmptyIndexAfterDeleteMessage();
        } catch (InvalidIndexException | NumberFormatException e) {
            ui.printInvalidDoneOrDeleteMessage();
        } catch (TaskIndexOutOfBoundsException e) {
            ui.printIndexOutOfBoundsMessage();
        }
    }
}
