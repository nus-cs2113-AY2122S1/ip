package duke.command;

import duke.exception.InvalidIndexException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.program.LizUi;
import duke.program.TaskList;
import duke.task.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    private String[] lineArgs;

    public DeleteCommand(String[] lineArgs) {
        this.lineArgs = lineArgs;
    }

    @Override
    public void executeCommand(TaskList tasks, LizUi ui) {
        deleteTaskWithExceptionHandling(lineArgs, tasks, ui);
    }

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
