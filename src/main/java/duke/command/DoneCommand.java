package duke.command;

import duke.exception.InvalidIndexException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.program.LizUi;
import duke.program.TaskList;
import duke.task.Task;

import java.util.ArrayList;

public class DoneCommand extends Command {

    private String[] lineArgs;

    public DoneCommand(String[] lineArgs) {
        this.lineArgs = lineArgs;
    }

    @Override
    public void executeCommand(TaskList tasks, LizUi ui) {
        markAsDoneWithExceptionHandling(lineArgs, tasks, ui);
    }

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
