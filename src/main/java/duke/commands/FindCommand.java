package duke.commands;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.util.Locale;

/**
 * Finds and shows tasks that contain a specified keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows tasks that match the provided keyword. "
            + "Parameters: KEYWORD"
            + "\n"
            + "Example: " + COMMAND_WORD
            + " book\n";
    public static final String MESSAGE_NO_MATCH = "No matching tasks found!";
    public static final String MESSAGE_SHOW_MATCHES = "Here are the matching tasks in your list: ";

    private final String keyword;

    /**
     * Constructor using raw values.
     *
     * @param keyword the word that is used to find matching tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Iterates through the task list to find tasks that match the keyword and prints the result to the user.
     *
     * @param tasks   the task list
     * @param ui      accesses text ui that shows messages to the user
     * @param storage access a text file which stores the task list
     */
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        int matchCount = 0;
        for (Task task : tasks.getList()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                if (matchCount == 0) {
                    ui.showToUser(MESSAGE_SHOW_MATCHES);
                }
                ui.showToUser((tasks.getIndex(task)+1) + "." + task);
                matchCount++;
            }
        }
        if (matchCount == 0) {
            ui.showToUser(MESSAGE_NO_MATCH);
        }
    }
}
