package duke.commands;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows tasks that match the provided keyword. "
            + "Parameters: KEYWORD"
            + "\n|| "
            + "Example: " + COMMAND_WORD
            + " book\n";
    public static final String MESSAGE_NO_MATCH = "No matching tasks found!";
    public static final String MESSAGE_SHOW_MATCHES = "Here are the matching tasks in your list: ";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        int counter = 0;
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(keyword)) {
                if (counter == 0) {
                    ui.showToUser(MESSAGE_SHOW_MATCHES);
                }
                ui.showToUser((counter + 1) + "." + task);
                counter++;
            }
        }
        if (counter == 0) {
            ui.showToUser(MESSAGE_NO_MATCH);
        }
    }
}
