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

    @Override
    public void executeCommand(TaskList tasks, LizUi ui) {
        findMatchingTasksWithExceptionHandling(tasks, lineArgs, ui);
    }

    private void findMatchingTasksWithExceptionHandling(TaskList tasks, String[] lineArgs, LizUi ui) {
        try {
            ArrayList<Task> matchingTasks = tasks.findMatchingTasks(tasks, lineArgs, ui);
            ui.printTaskList(matchingTasks.size(), matchingTasks, true);
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionMessage(COMMAND_FIND);
        }
    }
}
