package Duke.Command;

import Duke.Storage.Storage;
import Duke.Ui.Ui;
import Duke.Tasks.Task;
import Duke.Tasks.TaskList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": find a task in the list by key word.\n"
            + " Parameters: DESCRIPTION\n"
            + " Example: " + COMMAND_WORD + " read";

    private final String target;

    public FindCommand(String target) {
        this.target = target;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.find(this.target).size() == 0) {
            ui.printNoMatchingTaskMessage();
        } else {
            ui.printFindMessage();
            for (Task task : tasks.find(this.target)) {
                System.out.println(task.toString());
            }
        }
    }
}
