package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Shows the user all tasks related to his query
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes FindCommand by printing all tasks matching the user query
     *
     * @param tasks   the TaskList that contains all current tasks
     * @param storage the storage object responsible for reading from/writing to the data file
     * @param ui      the ui object responsible for acknowledging to user that the command has been executed
     */
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ArrayList<Task> results = (ArrayList<Task>) tasks.getTasks().stream()
                .filter(t -> t.getTask().contains(query))
                .collect(Collectors.toList());
        ui.showSearchResults(results);
    }
}
