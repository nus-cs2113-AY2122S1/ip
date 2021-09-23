package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.program.LizUi;
import duke.program.TaskList;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    private static final String COMMAND_FIND = "find";

    private String[] lineArgs;

    public FindCommand(String[] lineArgs) {
        this.lineArgs = lineArgs;
    }

    /**
     * Execute command to find all tasks that match the given keyword in the task list.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    @Override
    public void executeCommand(TaskList tasks, LizUi ui) {
        findMatchingTasksWithExceptionHandling(tasks, lineArgs, ui);
    }

    /**
     * Finds matching tasks with corresponding exception handling. Checked exceptions when caught, print
     * a corresponding error message.
     * @param lineArgs parsed array of line arguments. Contains find command and keyword string.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    private void findMatchingTasksWithExceptionHandling(TaskList tasks, String[] lineArgs, LizUi ui) {
        try {
            ArrayList<Task> matchingTasks = tasks.findMatchingTasks(tasks, lineArgs);
            ui.printTaskList(matchingTasks.size(), matchingTasks, true);
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionMessage(COMMAND_FIND);
        }
    }
}
