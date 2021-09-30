package commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks.
 */
public class FindTaskCommand extends Command {
    private final String input;

    /**
     * Changes the input attribute to the one given by the user.
     *
     * @param input The input by the user.
     */
    public FindTaskCommand(String input) {
        this.input = input.toLowerCase();
    }

    /**
     * Shows the tasks that can be found using the user's input to the user. If there are no such tasks,
     * the user will be shown a text message to reflect that.
     * If there is no input after the command word, the user will be prompted to include it.
     *
     * @param tasks Task list to be used to find the tasks.
     * @param ui Access to messages.
     * @param storage storage access to allow find to extend command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (input.trim().length() <= 4) {
            ui.showNoKeywordSpecifiedMessage();
        } else {
            String keyword = input.substring(5).trim();
            List<Integer> foundTasksIndices = new ArrayList<>();
            int index = 1;
            for (Task task : tasks.getTaskList()) {
                if (task.getDescription().toLowerCase().contains(keyword)) {
                    foundTasksIndices.add(index);
                }
                index++;
            }
            if (foundTasksIndices.size() <= 0) {
                ui.showTaskNotFoundMessage();
            } else {
                ui.showFoundTaskMessage();
                for (int taskIndex : foundTasksIndices) {
                    System.out.println(" " + taskIndex + "." + tasks.getTaskFromList(taskIndex - 1));
                }
                ui.showLines();
            }
        }
    }
}
