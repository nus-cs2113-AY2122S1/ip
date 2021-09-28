package Command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a <code>Command</code> to filter lists by keywords.
 */
public class FindCommand extends Command {
    /**
     * Instantiates a <code>FindCommand</code>.
     *
     * @param description Description of the <code>Command</code>.
     */
    public FindCommand(String description) {
        super(description, IS_NOT_EXIT);
    }

    /**
     * Prints list with certain keywords.
     *
     * @param tasks Tasks to be executed on.
     * @param ui UI to be used.
     * @param storage Storage to be used.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        printMatchingDescription(ui, tasks);
    }

    private void printMatchingDescription(Ui ui, TaskList tasks) {
        String wordFilter = getWordFilter(getDescription());
        ArrayList<Task> filteredTasks = getFilteredTasks(tasks, wordFilter);

        ui.printHorizontalLine();
        ui.printMatchingDescriptionPrompt();
        for (int i = 0; i < getTasksSize(filteredTasks); i++) {
            int taskNumber = i + 1;
            Task task = filteredTasks.get(i);
            ui.printTask(taskNumber, task);
        }
        ui.printHorizontalLine();
    }

    private ArrayList<Task> getFilteredTasks(TaskList tasks, String wordFilter) {
        ArrayList<Task> filteredTasks = (ArrayList<Task>) tasks.getTasks().stream()
                .filter(t -> t.getTaskName().contains(wordFilter))
                .collect(Collectors.toList());

        return filteredTasks;
    }

    private String getWordFilter(String description) {
        String words = description.substring(LENGTH_FIND);
        return words.trim();
    }
}
